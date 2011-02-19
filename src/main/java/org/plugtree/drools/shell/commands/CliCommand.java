package org.plugtree.drools.shell.commands;

import jline.Completor;
import org.drools.command.Command;

import java.util.List;

/**
 * creation date: 2/18/11
 */
public interface CliCommand {

    Completor getCompletor();

    Command<?> getCommand(String... args);
}
