package org.plugtree.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.command.runtime.rule.RetractCommand;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.plugtree.drools.ext.ObjectStore;

/**
 * creation date: 3/2/11
 */
public class ContextAwareRetractObjectCommand implements GenericCommand<ObjectStoreOperationResult> {

    private ObjectStore objectStore;
    private Integer objectId;

    public ContextAwareRetractObjectCommand(ObjectStore objectStore) {
        this.objectStore = objectStore;
    }

    @Override
    public ObjectStoreOperationResult execute(Context context) {
        if( objectId == null)
            throw new IllegalStateException("You must set objectId for retract");
        final Object object = objectStore.get(objectId);
        if( object == null )
            throw new IllegalArgumentException("The id [" + objectId + "] doesn't refer to any object" );
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        final FactHandle handle = ksession.getFactHandle(object);
        new RetractCommand(handle).execute(context);
        objectStore.remove(objectId);
        return new ObjectStoreOperationResult(objectId, object);
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
