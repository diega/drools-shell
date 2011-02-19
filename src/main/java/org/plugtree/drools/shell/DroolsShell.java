package org.plugtree.drools.shell;

import org.drools.command.Command;
import org.drools.runtime.StatefulKnowledgeSession;
import org.plugtree.drools.commands.RulesByPackageCommand;
import org.plugtree.drools.commands.RulesForPackageCommand;
import org.plugtree.drools.shell.commands.CliCommand;
import org.plugtree.drools.shell.commands.ListRulesCliCommand;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;
import org.plugtree.drools.shell.outputbuilders.OutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesByPackageOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesForPackageOutputBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * creation date: 2/17/11
 */
public class DroolsShell {

    private static Map<String, CliCommand> commands;
    private static Map<Class<? extends Command<?>>, OutputBuilder<?>> outputBuilders;
    private StatefulKnowledgeSession currentSession;

    private static final Logger logger = LoggerFactory.getLogger(DroolsShell.class);

    public DroolsShell(StatefulKnowledgeSession ksession) {
        this.currentSession = ksession;
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

    static{
        commands = new HashMap<String, CliCommand>(){{
            put("lsrules", new ListRulesCliCommand());
        }};
        outputBuilders = new HashMap<Class<? extends Command<?>>, OutputBuilder<?>>(){{
            put(RulesByPackageCommand.class, new RulesByPackageOutputBuilder());
            put(RulesForPackageCommand.class, new RulesForPackageOutputBuilder());
        }};
    }
}
