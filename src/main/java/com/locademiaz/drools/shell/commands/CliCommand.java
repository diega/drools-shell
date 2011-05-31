package com.locademiaz.drools.shell.commands;

import jline.Completor;
import org.drools.command.Command;
import com.locademiaz.drools.shell.exceptions.HelpRequestedException;
import com.locademiaz.drools.shell.exceptions.UnknownArgumentException;

/**
 * creation date: 2/18/11
 */
public interface CliCommand {

    Completor getCompletor();

    Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException;
}
