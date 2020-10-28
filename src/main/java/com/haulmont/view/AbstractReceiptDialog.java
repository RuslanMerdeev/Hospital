package com.haulmont.view;

import com.haulmont.entities.*;
import com.vaadin.ui.*;

/**
 * Class for Receipt Dialog with common Doctor and Patient Select Interface;
 * Description, Doctor, Patient, CreationDate and Validuty text fields;
 * Priority select field
 * @author r.merdeev
 */
public abstract class AbstractReceiptDialog extends AbstractCancelDialog {
    /** Doctor Select Interface */
    protected final SelectInterface selectedDoctor;
    
    /** Patient Select Interface */
    protected final SelectInterface selectedPatient;
    
    /** Description text field */
    protected final TextField description = new TextField("Description");
    
    /** Doctor text field */
    protected final TextField doctor = new TextField("Doctor");
    
    /** Patient text field */
    protected final TextField patient = new TextField("Patient");
    
    /** CreationDate text field */
    protected final TextField creationDate = new TextField("Creation Date");
    
    /** Validity text field */
    protected final TextField validity = new TextField("Validity");
    
    /** Priority select field */
    protected final NativeSelect<Priority> priority = new NativeSelect<>("Priority");
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     * @param selectedDoctor Doctor Select Interface
     * @param selectedPatient Patient Select Interface
     */
    public AbstractReceiptDialog(String caption, TabInterface tab, SelectInterface selectedDoctor, SelectInterface selectedPatient) {
        super(caption, tab);
        this.selectedDoctor = selectedDoctor;
        this.selectedPatient = selectedPatient;
    }
    
    @Override
    public AbstractDialog create() {
        fields();
        return super.create();
    }
    
    /**
     * Adds fields to vertical layout
     */
    protected void fields() {
        vertical.addComponents(description, doctor, patient, creationDate, validity, priority);
        vertical.setComponentAlignment(description, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(doctor, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(patient, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(creationDate, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(validity, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(priority, Alignment.TOP_CENTER);
        priority.setItems(Priority.getValues());
        doctor.setEnabled(false);
        patient.setEnabled(false);
        creationDate.setEnabled(false);
    }   
}
