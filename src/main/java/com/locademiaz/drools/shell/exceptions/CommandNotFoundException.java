package com.locademiaz.drools.shell.exceptions;

/**
 * creation date: 2/17/11
 */
public class CommandNotFoundException extends Exception {

    private String commandNotFound;

    public CommandNotFoundException(String s) {
        super(s + ": Command not found");
        this.commandNotFound = s;
    }

    public String getCommandNotFound(){
        return commandNotFound;
    }
}
