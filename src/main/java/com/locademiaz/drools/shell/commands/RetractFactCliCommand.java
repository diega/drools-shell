package com.locademiaz.drools.shell.commands;

import jline.Completor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.drools.command.Command;
import com.locademiaz.drools.commands.ContextAwareRetractObjectCommand;
import com.locademiaz.drools.shell.exceptions.HelpRequestedException;
import com.locademiaz.drools.shell.exceptions.UnknownArgumentException;

import java.util.Arrays;

/**
 * creation date: 3/2/11
 */
public class RetractFactCliCommand extends CliCommandSupport {

    private ContextAwareRetractObjectCommand retractObjectCommand;
    private OptionParser parser;
    private OptionSpec<Integer> idOpt;
    private OptionSpec<Void> helpOpt;

    public RetractFactCliCommand(ContextAwareRetractObjectCommand retractObjectCommand) {
        this.retractObjectCommand = retractObjectCommand;
        parser = new OptionParser();
        idOpt = parser.acceptsAll(Arrays.asList("i", "id"), "Object ID to retract")
                .withRequiredArg().ofType(Integer.class);
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
        checkMandatoryOption(optionSet, idOpt);

        final Integer objectId = optionSet.valueOf(idOpt);
        retractObjectCommand.setObjectId(objectId);
        return retractObjectCommand;
    }
}
