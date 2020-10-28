package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Interface to Control DB Table
 * @author r.merdeev
 */
public interface ControlInterface {
    
    /**
     * Adds Entity to Table
     * @param entity Entity
     */
    public void add(AbstractEntity entity);
    
    /**
     * Edits Entity in Table
     * @param entity Entity
     */
    public void edit(AbstractEntity entity); 
    
    /**
     * Deletes Entity from Table
     * @param entity Entity
     */
    public void delete(AbstractEntity entity);
}
