package com.locademiaz.drools.shell.outputbuilders;

/**
 * creation date: 2/17/11
 */
public interface OutputBuilder<T> {

    static final String CR = System.getProperty("line.separator");

    String getOutput(T input);
}
