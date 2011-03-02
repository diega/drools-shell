package org.plugtree.drools.shell.outputbuilders;

/**
 * creation date: 3/2/11
 */
public class FireAllRulesOutputBuilder implements OutputBuilder<Integer> {
    @Override
    public String getOutput(Integer rulesFired) {
        return rulesFired + " rules fired";
    }
}
