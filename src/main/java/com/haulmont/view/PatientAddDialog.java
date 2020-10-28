package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Class for Patient Add Dialog
 * @author r.merdeev
 */
public class PatientAddDialog extends AbstractPatientDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public PatientAddDialog(String caption, TabInterface tab) {
        super(caption, tab);   
    }

    @Override
    protected void ok() {
        Patient patient = null;
        try {
            patient = new Patient(0, firstName.getValue(), middleName.getValue(), lastName.getValue(), phone.getValue());
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        }
        tab.add(patient);
        close();
    }
}
