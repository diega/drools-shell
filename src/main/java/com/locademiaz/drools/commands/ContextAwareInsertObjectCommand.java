package com.locademiaz.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import com.locademiaz.drools.ext.ObjectStore;

/**
 * creation date: 3/1/11
 */
public class ContextAwareInsertObjectCommand implements GenericCommand<ObjectStoreOperationResult> {

    private ObjectStore objectStore;
    private Object objectToInsert;

    public ContextAwareInsertObjectCommand(ObjectStore objectStore) {
        this.objectStore = objectStore;
    }

    @Override
    public ObjectStoreOperationResult execute(Context context) {
        if( objectToInsert == null )
            throw new IllegalStateException("You must set object to insert first");
        final FactHandle handle = new InsertObjectCommand(objectToInsert).execute(context);
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        final Object insertedObject = ksession.getObject(handle);
        final int assignedId = objectStore.put(insertedObject);
        return new ObjectStoreOperationResult(assignedId, insertedObject);
    }

    public void setObject(Object outputObject) {
        this.objectToInsert = outputObject;
    }
}
