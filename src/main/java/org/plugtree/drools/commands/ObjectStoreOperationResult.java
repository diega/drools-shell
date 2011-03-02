package org.plugtree.drools.commands;

/**
 * creation date: 3/2/11
 */
public class ObjectStoreOperationResult {

    private Object insertedObject;
    private int id;

    public ObjectStoreOperationResult(int id, Object insertedObject) {
        this.id = id;
        this.insertedObject = insertedObject;
    }

    public int getId() {
        return id;
    }

    public Object getInsertedObject() {
        return insertedObject;
    }
}
