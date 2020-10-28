package com.haulmont.entities;

import com.haulmont.utils.Log;
import java.util.*;

/**
 * Class for priority
 * @author r.merdeev
 */
public class Priority {
    /** Priority state is normal */
    public static final byte NORMAL = 0;
    
    /** Priority state is cito */
    public static final byte CITO = 1;
    
    /** Priority state is statim */
    public static final byte STATIM = 2;
    
    /** Priority state */
    private final byte state;

    /**
     * Constructor
     * @param state Priority state
     * @throws java.lang.InstantiationException
     */
    public Priority(byte state) throws InstantiationException {
        if (state > STATIM) throw new InstantiationException();
        this.state = state;
    }
    
    /**
     * Returns List of Priorities with available values 
     * @return list of Priorities
     */
    public static List<Priority> getValues() {
        try {
            return Arrays.asList(new Priority(Priority.NORMAL), new Priority(Priority.CITO), new Priority(Priority.STATIM));    
        }
        catch(InstantiationException e) {
            Log.exception("Priority", e);
        }
        return null;
    }
    
    /**
     * Returns Priority state
     * @return state
     */
    public byte get() {
        return state;
    }
    
    @Override
    public String toString() {
        switch (state) {
            case NORMAL:
                return "NORMAL";
            case CITO:
                return "CITO";
            case STATIM:
                return "STATIM";
        }
        return "";
    }
    
    @Override
    public boolean equals(Object priority) {
        if (priority != null && priority.getClass() == Priority.class) return get() == ((Priority)priority).get();
        return false;
    }

    @Override
    public int hashCode() {        
        return this.state;
    }
}
