package com.haulmont.entities;

/** 
 * Class for doctor
 * @author r.merdeev
 */
public class Doctor extends AbstractHuman {
    /** Speciality length constant */
    public static int SPECIALITY_LEN = 20;
    
    /** Doctor speciality */
    private final String speciality;

    /**
     * Constructor
     * @param key Doctor key
     * @param firstName Doctor first name
     * @param middleName Doctor middle name
     * @param lastName Doctor last name
     * @param speciality Doctor speciality
     * @throws java.lang.InstantiationException
     */
    public Doctor(long key, String firstName, String middleName, String lastName, String speciality) throws InstantiationException {
        super(key, firstName, middleName, lastName);
        if (speciality.length() > SPECIALITY_LEN) throw new InstantiationException();            
        this.speciality = speciality;
    }

    /**
     * Returns Doctor speciality
     * @return speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + speciality;
    }
}
