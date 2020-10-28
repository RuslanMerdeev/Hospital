package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Class for Doctor Edit Dialog
 * @author r.merdeev
 */
public class DoctorEditDialog extends AbstractDoctorDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public DoctorEditDialog(String caption, TabInterface tab) {
        super(caption, tab);        
    }

    @Override
    protected void ok() {
        Doctor doctor = null;
        try {
            doctor = new Doctor(tab.get().getKey(), firstName.getValue(), middleName.getValue(), lastName.getValue(), speciality.getValue());           
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        }  
        tab.edit(doctor);
        close();
    }
    
    @Override
    protected void fields() {
        super.fields();
        Doctor doctor = (Doctor)tab.get();
        if (doctor != null) {
            firstName.setValue(doctor.getFirstName());
            middleName.setValue(doctor.getMiddleName());
            lastName.setValue(doctor.getLastName());
            speciality.setValue(doctor.getSpeciality());
        }
    }
}
