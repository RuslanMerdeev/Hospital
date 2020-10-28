package com.haulmont.entities;

/**
 * Class for entity with exclusive access key
 * @author r.merdeev
 */
public abstract class AbstractEntity {
    /** Entity key */ 
    private final long key;

    /**
     * Constructor
     * @param key Entity key
     */
    AbstractEntity(long key) {
        this.key = key;
    }

    /**
     * Returns Entity key
     * @return key
     */
    public long getKey() {
        return key;
    }

    /**
     * Returns equality of two Entities
     * @param o Entity object
     * @return equality
     */
    public boolean equals(AbstractEntity o){
        return (key == o.key);
    }

    @Override
    public String toString(){
        return "key: " + getKey();
    }
}
