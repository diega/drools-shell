package com.locademiaz.drools.shell.outputbuilders;

import com.locademiaz.drools.commands.ObjectStoreOperationResult;

/**
 * creation date: 2/20/11
 */
public class InsertedObjectOutputBuilder implements OutputBuilder<ObjectStoreOperationResult> {

    @Override
    public String getOutput(ObjectStoreOperationResult input) {
        StringBuilder builder = new StringBuilder("[")
                .append(input.getId()).append("] = ")
                .append(input.getInsertedObject().toString());
        return builder.toString();
    }
}
