package com.haulmont.entities;

import com.haulmont.utils.Log;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Receipt extends AbstractEntity {
    /** Description length constant */
    public static int DESCRIPTION_LEN = 100;
    
    /** Receipt description length for toString */
    private final static int DSCR_LEN = 20;
    
    /** Receipt description */
    private final String description;
    
    /** Receipt doctor */
    private final AbstractEntity doctor;
    
    /** Receipt patient */
    private final AbstractEntity patient;
    
    /** Receipt creation date */
    private final Date creationDate;
    
    /** Receipt validity */
    private final int validity;
    
    /** Receipt priority */
    private final Priority priority;

    /**
     * Constructor
     * @param key Receipt key
     * @param description Receipt description
     * @param doctor Receipt doctor
     * @param patient Receipt patient
     * @param creationDate Receipt creation date
     * @param validity Receipt validity
     * @param priority Receipt priority
     * @throws java.lang.InstantiationException
     */
    public Receipt(long key, String description, AbstractEntity doctor, AbstractEntity patient, Date creationDate, int validity, Priority priority) throws InstantiationException {
        super(key);
        if (description.length() > DESCRIPTION_LEN) throw new InstantiationException();
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
        this.creationDate = creationDate;
        this.validity = validity;
        this.priority = priority;
    }
    
    /** 
     * Returns parsed Receipt validity from String
     * @param validity Receipt validity
     * @return validity
     */
    public static int parseValidity(String validity) {
        Integer res = null;
        try {
            res = Integer.parseInt(validity);
        } 
        catch (NumberFormatException e) {
            Log.exception("Parse Validity", e);
        }
        return res;
    }

    /**
     * Returns Receipt description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns Receipt patient
     * @return patient
     */
    public AbstractEntity getPatient() {
        return patient;
    }

    /** 
     * Returns Receipt doctor
     * @return doctor
     */
    public AbstractEntity getDoctor() {
        return doctor;
    }

    /** 
     * Returns Receipt creation date
     * @return creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }
    
    /** 
     * Returns Receipt creation date in String
     * @return creation date
     */
    public String getStringCreationDate() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(creationDate);
    }

    /**
     * Returns Receipt validity
     * @return validity
     */
    public int getValidity() {
        return validity;
    }

    /**
     * Returns Receipt priority
     * @return priority
     */
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return description.length() <= DSCR_LEN ? description : description.substring(0, DSCR_LEN) + "..."
                + " p: " + patient.toString() 
                + " d: " + doctor.toString() 
                + " " + getStringCreationDate()
                + " " + validity
                + " " + priority.toString();
    }
}
