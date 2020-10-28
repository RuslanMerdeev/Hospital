package com.haulmont.view;

import com.haulmont.entities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for Receipt Add Dialog
 * @author r.merdeev
 */
public class ReceiptAddDialog extends AbstractReceiptDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     * @param selectedDoctor Doctor Select Intreface
     * @param selectedPatient Patient Select Interface
     */
    public ReceiptAddDialog(String caption, TabInterface tab, SelectInterface selectedDoctor, SelectInterface selectedPatient) {
        super(caption, tab, selectedDoctor, selectedPatient);        
    }

    @Override
    protected void ok() {
        Receipt receipt = null;
        try {
            receipt = new Receipt(0, description.getValue(), selectedDoctor.get(), selectedPatient.get(), java.sql.Date.valueOf(creationDate.getValue()), Receipt.parseValidity(validity.getValue()), priority.getSelectedItem().get());            
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        }
        tab.add(receipt);
        close();
    }
    
    @Override
    protected void fields() {
        super.fields();
        if (selectedDoctor.get() != null && selectedPatient.get() != null) {
            creationDate.setValue((new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis())));    
            doctor.setValue(((Doctor)selectedDoctor.get()).toString());
            patient.setValue(((Patient)selectedPatient.get()).toString());
        }
    }
}
