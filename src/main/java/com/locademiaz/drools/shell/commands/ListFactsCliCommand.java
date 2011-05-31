package com.locademiaz.drools.shell.commands;

import jline.Completor;
import org.drools.command.Command;
import org.drools.command.runtime.rule.GetObjectsCommand;
import com.locademiaz.drools.shell.exceptions.HelpRequestedException;
import com.locademiaz.drools.shell.exceptions.UnknownArgumentException;

/**
 * creation date: 2/20/11
 */
public class ListFactsCliCommand implements CliCommand{
    @Override
    public Completor getCompletor() {
        return null; 
    }

    @Override
    public Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException {
        return new GetObjectsCommand();
    }
}
