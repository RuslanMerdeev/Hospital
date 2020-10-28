package com.haulmont.db;

/**
 * Interface to get by key
 * @author r.merdeev
 * @param <E> DB entity
 * @param <K> DB key
 */
public interface KeyInterface <E, K> {
    
    /**
     * Returns Entity by key
     * @param key key
     * @return entity
     */
    public abstract E getByKey(K key);
}
