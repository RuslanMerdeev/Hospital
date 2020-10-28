package com.haulmont.entities;

/**
 * Class for patient
 * @author r.merdeev
 */
public class Patient extends AbstractHuman {
    /** Phone length constant */
    public static int PHONE_LEN = 20;
    
    /** Patient phone number */
    private final String phone;

    /**
     * Constructor
     * @param key Patient key
     * @param firstName Patient first name
     * @param middleName Patient middle name
     * @param lastName Patient last name
     * @param phone Patient phone number
     * @throws java.lang.InstantiationException
     */
    public Patient(long key, String firstName, String middleName, String lastName, String phone) throws InstantiationException {
        super(key, firstName, middleName, lastName);
        if (phone.length() > PHONE_LEN) throw new InstantiationException();
        this.phone = phone;
    }

    /**
     * Patient phone number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + phone;
    }
}
