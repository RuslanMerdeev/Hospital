package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Class for Patient Edit Dialog
 * @author r.merdeev
 */
public class PatientEditDialog extends AbstractPatientDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public PatientEditDialog(String caption, TabInterface tab) {
        super(caption, tab);        
    }

    @Override
    protected void ok() {
        Patient patient = null;
        try {
            patient = new Patient(tab.get().getKey(), firstName.getValue(), middleName.getValue(), lastName.getValue(), phone.getValue());           
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        }
        tab.edit(patient);
        close();
    }
    
    @Override
    protected void fields() {
        super.fields();
        Patient patient = (Patient)tab.get();
        if (patient != null) {
            firstName.setValue(patient.getFirstName());
            middleName.setValue(patient.getMiddleName());
            lastName.setValue(patient.getLastName());
            phone.setValue(patient.getPhone());
        }
    }
}
