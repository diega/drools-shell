package org.plugtree.drools.ext;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * creation date: 3/1/11
 */
public class ObjectStore {

    private SortedMap<Integer, Object> accesedObjectStore = new TreeMap<Integer, Object>();

    public int put(Object object){
        int newKey;
        if(accesedObjectStore.isEmpty())
            newKey = 1;
        else
            newKey = accesedObjectStore.lastKey() + 1;
        accesedObjectStore.put(newKey, object);
        return newKey;
    }

    public Object get(int id){
        return accesedObjectStore.get(id);
    }

    public void remove(int objectId) {
        accesedObjectStore.remove(objectId);
    }

    public boolean isEmpty() {
        return accesedObjectStore.isEmpty();
    }
}
