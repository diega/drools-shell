package org.plugtree.drools.shell.commands;

import jline.Completor;
import org.drools.command.Command;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.plugtree.drools.shell.exceptions.HelpRequestedException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;

/**
 * creation date: 3/2/11
 */
public class FireAllRulesCliCommand extends CliCommandSupport{
    @Override
    public Completor getCompletor() {
        return null;
    }

    @Override
    public Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException {
        return new FireAllRulesCommand();
    }
}
