package org.plugtree.drools.shell.outputbuilders;

import org.drools.runtime.rule.FactHandle;

/**
 * creation date: 2/20/11
 */
public class InsertedObjectOutputBuilder implements OutputBuilder<InsertedObjectResult> {

    @Override
    public String getOutput(InsertedObjectResult input) {
        StringBuilder builder = new StringBuilder("[")
                .append(input.getId()).append("] = ")
                .append(input.getInsertedObject().toString());
        return builder.toString();
    }
}
