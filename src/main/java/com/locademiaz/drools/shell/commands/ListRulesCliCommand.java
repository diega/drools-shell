package com.locademiaz.drools.shell.commands;

import jline.Completor;
import joptsimple.*;
import org.drools.command.Command;
import com.locademiaz.drools.commands.RulesByPackageCommand;
import com.locademiaz.drools.commands.RulesForPackageCommand;
import com.locademiaz.drools.shell.exceptions.HelpRequestedException;
import com.locademiaz.drools.shell.exceptions.UnknownArgumentException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * creation date: 2/18/11
 */
public class ListRulesCliCommand implements CliCommand {

    private OptionSpec<String> packageNameOpt;
    private OptionParser parser;
    private OptionSpec<Void> helpOpt;

    public ListRulesCliCommand() {
        parser = new OptionParser();
        packageNameOpt = parser.acceptsAll(Arrays.asList(new String[]{"p","packageNameOpt"}), "Package Name")
                .withOptionalArg().ofType(String.class);
        helpOpt = parser.acceptsAll(Arrays.asList(new String[]{"?", "help", "h"}), "This help");
    }

    @Override
    public Completor getCompletor() {
        return null;
    }

    @Override
    public Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException {
        OptionSet optionSet = null;
        try {
            optionSet = parser.parse(args);
        } catch (OptionException oe) {
            throw new UnknownArgumentException(new ArrayList<String>(oe.options()));
        }

        if(optionSet.has(helpOpt)){
            StringWriter helpBuffer = new StringWriter();
            try {
                parser.printHelpOn(helpBuffer);
            } catch (IOException e) {
                // in case of error we don't print any help message
            } finally{
                try {
                    helpBuffer.close();
                } catch (IOException e) {
                }
            }
            throw new HelpRequestedException(this, helpBuffer.toString());
        }

        if(!optionSet.nonOptionArguments().isEmpty())
            throw new UnknownArgumentException(optionSet.nonOptionArguments());
        if(optionSet.has(packageNameOpt)){
            String packageName = packageNameOpt.value(optionSet);
            return new RulesForPackageCommand(packageName);
        }
        else
            return new RulesByPackageCommand();
    }

}
