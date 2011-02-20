package org.plugtree.drools.commands;

import org.drools.command.Command;
import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;

/**
 * creation date: 2/20/11
 */
public class DummyCommand implements GenericCommand<String> {
    @Override
    public String execute(Context context) {
        return "dummy command executed";
    }
}
