package org.plugtree.drools.shell.outputbuilders;

/**
 * creation date: 2/17/11
 */
public interface OutputBuilder<T> {

    public String getOutput(T input);
}
