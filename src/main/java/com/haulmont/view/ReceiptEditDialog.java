package com.haulmont.view;

import com.haulmont.entities.*;

/**
 * Class for Receipt Edit Dialog
 * @author r.merdeev
 */
public class ReceiptEditDialog extends AbstractReceiptDialog {
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public ReceiptEditDialog(String caption, TabInterface tab) {
        super(caption, tab, null, null);          
    }

    @Override
    protected void ok() {
        Receipt receipt = null;
        try {
            receipt = new Receipt(tab.get().getKey(), description.getValue(), ((Receipt)tab.get()).getDoctor(), ((Receipt)tab.get()).getPatient(), ((Receipt)tab.get()).getCreationDate(), Receipt.parseValidity(validity.getValue()), priority.getSelectedItem().get());           
        }
        catch (Exception e) {
            tab.show(new ErrorDialog().create("Wrong field"));
        } 
        tab.edit(receipt);
        close();
    }
    
    @Override
    protected void fields() {
        super.fields();
        Receipt receipt = (Receipt)tab.get();        
        if (receipt != null) {
            doctor.setValue(receipt.getDoctor().toString());
            patient.setValue(receipt.getPatient().toString());
            description.setValue(receipt.getDescription());
            creationDate.setValue(receipt.getStringCreationDate());
            validity.setValue(Integer.toString(receipt.getValidity()));
            priority.setSelectedItem(receipt.getPriority());
        }
    }
}
