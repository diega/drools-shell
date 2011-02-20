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
    private StatefulKnowledgeSession currentSession;

    private static final Logger logger = LoggerFactory.getLogger(DroolsShell.class);

    public DroolsShell(StatefulKnowledgeSession ksession, HashMap<String, CliCommand> commands, HashMap<Class<? extends Command<?>>, OutputBuilder<?>> outputBuilders) {
        this.currentSession = ksession;
        this.commands = commands;
        this.outputBuilders = outputBuilders;
    }

    public String run(String inputCommand, String... args) throws CommandNotFoundException{
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
        
        final OutputBuilder outputBuilder = outputBuilders.get(command.getClass());
        Object executionResult = null;
        try {
            executionResult = currentSession.execute(command);
        } catch (Exception e){
            logger.trace("error executing command", e);
            return e.getMessage();
        }
        return outputBuilder.getOutput(executionResult);
    }

    public Set<String> getCliCommandNames(){
        return Collections.unmodifiableSet(commands.keySet());
    }
}
