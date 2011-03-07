package org.plugtree.drools.shell;

import jline.ConsoleReader;
import org.drools.command.Command;
import org.drools.runtime.StatefulKnowledgeSession;
import org.plugtree.drools.commands.RulesByPackageCommand;
import org.plugtree.drools.commands.RulesForPackageCommand;
import org.plugtree.drools.shell.commands.CliCommand;
import org.plugtree.drools.shell.commands.ListRulesCliCommand;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.plugtree.drools.shell.exceptions.HelpRequestedException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;
import org.plugtree.drools.shell.outputbuilders.OutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesByPackageOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesForPackageOutputBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * creation date: 2/17/11
 */
public class DroolsShell {

    private Map<String, CliCommand> commands;
    private Map<Class<? extends Command<?>>, OutputBuilder<?>> outputBuilders;

    private static final Logger logger = LoggerFactory.getLogger(DroolsShell.class);

    public DroolsShell(Map<String, CliCommand> commands, Map<Class<? extends Command<?>>, OutputBuilder<?>> outputBuilders) {
        this.commands = commands;
        this.outputBuilders = outputBuilders;
    }

    public String run(StatefulKnowledgeSession ksession, String inputCommand, String... args) throws CommandNotFoundException{
        CliCommand cliCommand = commands.get(inputCommand);
        Command<?> command = null;
        if(null == cliCommand)
            throw new CommandNotFoundException(inputCommand);
        try {
            command = cliCommand.getCommand(args);
        } catch (UnknownArgumentException uae) {
            logger.trace("unknown argument", uae);
            return uae.getMessage();
        } catch (HelpRequestedException hre) {
            return hre.getHelpMessage();
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }
        
        Object executionResult;
        try {
            executionResult = ksession.execute(command);
        } catch (Exception e){
            logger.trace("error executing command", e);
            return e.getMessage();
        }
        final OutputBuilder outputBuilder = outputBuilders.get(command.getClass());
        if(outputBuilder == null) {
            throw new RuntimeException( "Unable to find output builder for: " + command.getClass());
        }
        return outputBuilder.getOutput(executionResult);
    }
}
