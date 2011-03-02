package org.plugtree.drools.shell.outputbuilders;

/**
 * creation date: 3/2/11
 */
public class InsertedObjectResult {

    private Object insertedObject;
    private int id;

    public InsertedObjectResult(int id, Object insertedObject) {
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
