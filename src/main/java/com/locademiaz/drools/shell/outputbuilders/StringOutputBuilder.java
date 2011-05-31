package com.locademiaz.drools.shell.outputbuilders;

/**
 * creation date: 2/20/11
 */
public class StringOutputBuilder implements OutputBuilder<String>{
    @Override
    public String getOutput(String input) {
        return input;
    }
}
