package org.plugtree.drools.shell.commands;

import jline.Completor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.OptionSpecBuilder;
import org.drools.command.Command;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.plugtree.drools.commands.VerboseFireAllRules;
import org.plugtree.drools.shell.exceptions.HelpRequestedException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;

import java.util.Arrays;

/**
 * creation date: 3/2/11
 */
public class FireAllRulesCliCommand extends CliCommandSupport{
    private OptionParser parser;
    private OptionSpec<Void> verboseOpt;
    private OptionSpec<Void> helpOpt;


    public FireAllRulesCliCommand() {
        parser = new OptionParser();
        verboseOpt = parser.acceptsAll(Arrays.asList("v", "verbose"), "Verbose output");
        helpOpt = parser.acceptsAll(Arrays.asList("?", "help", "h"), "This help");
    }

    @Override
    public Completor getCompletor() {
        return null;
    }

    @Override
    public Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException {
        OptionSet optionSet = parseArguments(parser, args);
        isPrintHelp(optionSet, helpOpt, parser);

        if(optionSet.has(verboseOpt)) {
            return new VerboseFireAllRules();
        } else {
            return new FireAllRulesCommand();
        }
    }
}
