package com.locademiaz.drools.shell.exceptions;

import java.util.List;

/**
 * creation date: 2/19/11
 */
public class UnknownArgumentException extends Exception {

    private List<String> unknownArguments;

    public UnknownArgumentException(List<String> args) {
        super("Unknown argument: " + args);
        this.unknownArguments = args;
    }

    public List<String> getUnknownArguments() {
        return this.unknownArguments;
    }
}
