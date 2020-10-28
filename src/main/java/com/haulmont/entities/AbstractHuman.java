package com.haulmont.entities;

/**
 * Class for human
 * @author r.merdeev
 */
public abstract class AbstractHuman extends AbstractEntity {    
    /** FirstName length constant */
    public static int FIRST_NAME_LEN = 20;
    
    /** MiddleName length constant */
    public static int MIDDLE_NAME_LEN = 20;
    
    /** LastName length constant */
    public static int LAST_NAME_LEN = 20;
    
    /** Human first name */ 
    private final String firstName;
    
    /** Human middle name */
    private final String middleName;
    
    /** Human last name */
    private final String lastName;

    /**
     * Constructor
     * @param key Human key
     * @param firstName Human first name
     * @param middleName Human middle name
     * @param lastName Human last name 
     */
    AbstractHuman(long key, String firstName, String middleName, String lastName) throws InstantiationException {
        super(key);
        if (firstName.length() > FIRST_NAME_LEN) throw new InstantiationException(); 
        if (middleName.length() > MIDDLE_NAME_LEN) throw new InstantiationException();
        if (lastName.length() > LAST_NAME_LEN) throw new InstantiationException();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Returns Human first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
       
    /**
     * Returns Human middle name
     * @return middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Returns Human last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString(){
        return firstName + " " + middleName + " " + lastName;
    }

    /**
     * Returns whether any Human name contains sample or its part
     * @param sample sample to be contained
     * @return content
     */
    public boolean contains(String sample){
        String[] split = sample.split(" ");
        for (String s: split) {
            if (firstName.toLowerCase().contains(s.toLowerCase())
                    || middleName.toLowerCase().contains(s.toLowerCase())
                    || lastName.toLowerCase().contains(s.toLowerCase()))
                return true;
        }
        return false;
    }
}
