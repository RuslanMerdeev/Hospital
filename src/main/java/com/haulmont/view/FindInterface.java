package com.haulmont.view;

import com.haulmont.entities.*;
import java.util.List;

/**
 * Interface to find Receipts by Entity
 * @author r.merdeev
 */
public interface FindInterface {
    
    /**
     * Finds Receipts by Entity
     * @param entity Entity
     * @return list of Receipts
     */
    public List<Receipt> find(AbstractEntity entity);
}
