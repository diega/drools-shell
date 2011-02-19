package org.plugtree.drools.shell.commands;

import jline.Completor;
import joptsimple.*;
import org.drools.command.Command;
import org.plugtree.drools.commands.RulesByPackageCommand;
import org.plugtree.drools.commands.RulesForPackageCommand;

import java.util.Arrays;

/**
 * creation date: 2/18/11
 */
public class ListRulesCliCommand implements CliCommand {

    private OptionSpec<String> packageNameOpt;
    private OptionParser parser;

    public ListRulesCliCommand() {
        parser = new OptionParser();
        packageNameOpt = parser.acceptsAll(Arrays.asList(new String[]{"p","packageNameOpt"}), "Package Name")
                .withOptionalArg().ofType(String.class);
    }

    @Override
    public Completor getCompletor() {
        return null;
    }

    @Override
    public Command<?> getCommand(String... args) {
        final OptionSet optionSet = parser.parse(args);
        if(optionSet.has(packageNameOpt)){
            String packageName = packageNameOpt.value(optionSet);
            return new RulesForPackageCommand(packageName);
        }
        else
            return new RulesByPackageCommand();
    }

}
