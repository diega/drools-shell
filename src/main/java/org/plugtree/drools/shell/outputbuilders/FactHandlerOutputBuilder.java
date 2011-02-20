package org.plugtree.drools.shell.outputbuilders;

import org.drools.runtime.rule.FactHandle;

/**
 * creation date: 2/20/11
 */
public class FactHandlerOutputBuilder implements OutputBuilder<FactHandle> {
    @Override
    public String getOutput(FactHandle input) {
        return "Fact inserted: " + input.toExternalForm();
    }
}
