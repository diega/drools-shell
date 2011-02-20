package org.plugtree.drools.commands;

import org.drools.command.Command;
import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;

/**
 * creation date: 2/20/11
 */
public class DummyCommand implements GenericCommand<String> {

    private Object outputObject;

    public DummyCommand(Object outputObject) {
        this.outputObject = outputObject;
    }

    @Override
    public String execute(Context context) {
        return "Dummy output: " + outputObject.toString();
    }
}
