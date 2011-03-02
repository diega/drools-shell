package org.plugtree.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.plugtree.drools.ext.ObjectStore;
import org.plugtree.drools.shell.outputbuilders.InsertedObjectResult;

/**
 * creation date: 3/1/11
 */
public class ContextAwareInsertObjectCommand implements GenericCommand<ObjectStoreOperationResult> {

    private ObjectStore objectStore;
    private InsertObjectCommand insertObjectCommand;

    public ContextAwareInsertObjectCommand(ObjectStore objectStore) {
        this.objectStore = objectStore;
        this.insertObjectCommand = new InsertObjectCommand();
    }

    @Override
    public ObjectStoreOperationResult execute(Context context) {
        final FactHandle handle = insertObjectCommand.execute(context);
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        final Object insertedObject = ksession.getObject(handle);
        final int assignedId = objectStore.put(insertedObject);
        return new ObjectStoreOperationResult(assignedId, insertedObject);
    }

    public void setObject(Object object) {
        insertObjectCommand.setObject(object);
    }
}
