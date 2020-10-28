package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Class for Doctor Add Dialog
 * @author r.merdeev
 */
public class DoctorAddDialog extends AbstractDoctorDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public DoctorAddDialog(String caption, TabInterface tab) {
        super(caption, tab);   
    }

    @Override
    protected void ok() {
        Doctor doctor = null;
        try {
            doctor = new Doctor(0, firstName.getValue(), middleName.getValue(), lastName.getValue(), speciality.getValue());
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        } 
        tab.add(doctor);
        close();
    }
}
