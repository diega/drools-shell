package com.locademiaz.drools.shell.exceptions;

import com.locademiaz.drools.shell.commands.CliCommand;

/**
 * creation date: 2/19/11
 */
public class HelpRequestedException extends Exception {

    private String helpMessage;
    private CliCommand command;

    public HelpRequestedException(CliCommand command, String helpMessage) {
        this.helpMessage = helpMessage;
        this.command = command;
    }

    public String getHelpMessage(){
        return this.helpMessage;
    }

    public CliCommand getCommand(){
        return this.command;
    }
}
