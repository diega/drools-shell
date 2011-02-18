package org.plugtree.drools.shell;

import org.drools.command.Command;
import org.drools.definition.KnowledgePackage;
import org.drools.runtime.StatefulKnowledgeSession;
import org.plugtree.drools.shell.commands.ExtraCommands;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.plugtree.drools.shell.outputbuilders.KnowledgePackageCollectionOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.OutputBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * creation date: 2/17/11
 */
public class DroolsShell {

    private static Map<String, Command> commands;
    private static Map<Command, OutputBuilder> outputBuilders;
    private StatefulKnowledgeSession currentSession;

    public DroolsShell(StatefulKnowledgeSession ksession) {
        this.currentSession = ksession;
    }

    public String run(String inputCommand) throws CommandNotFoundException{
        Command command = commands.get(inputCommand);
        if(null == command)
            throw new CommandNotFoundException(inputCommand + ": command not found");
        return outputBuilders.get(command).getOutput(currentSession.execute(command));
    }

    static{
        final Command<Collection<KnowledgePackage>> packagesListCommand = ExtraCommands.getPackagesListCommand();
        commands = new HashMap<String, Command>(){{
            put("lspackage", packagesListCommand);
        }};
        outputBuilders = new HashMap<Command, OutputBuilder>(){{
            put(packagesListCommand, new KnowledgePackageCollectionOutputBuilder());
        }};
    }
}
