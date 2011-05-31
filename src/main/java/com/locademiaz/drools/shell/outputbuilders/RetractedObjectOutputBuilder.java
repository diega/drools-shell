package com.locademiaz.drools.shell.outputbuilders;

import com.locademiaz.drools.commands.ObjectStoreOperationResult;

/**
 * creation date: 3/2/11
 */
public class RetractedObjectOutputBuilder implements OutputBuilder<ObjectStoreOperationResult> {

    @Override
    public String getOutput(ObjectStoreOperationResult input) {
        return input.getInsertedObject().toString() + " retracted";
    }
}
