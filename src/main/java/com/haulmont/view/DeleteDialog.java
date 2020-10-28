package com.haulmont.view;

import com.vaadin.ui.*;
import com.haulmont.entities.*;

/**
 * Class for Delete Dialog with common label ok and cancel buttons
 * @author r.merdeev
 */
public class DeleteDialog extends AbstractCancelDialog {  
    /** Label */
    private Label label;
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Intreface
     */
    public DeleteDialog(String caption, TabInterface tab) {
        super(caption, tab);
    }
    
    @Override
    public AbstractDialog create() {
        label();
        return super.create();
    }
    
    @Override
    protected void ok() {
        tab.delete(tab.get());
        close();
    }
    
    /**
     * Checks selected Entity and adds label in vertical layout
     * @param text text of label
     */
    private void label() {        
        AbstractEntity entity = tab.get();
        if (entity != null) {
            label = new Label(entity.toString());
            vertical.addComponent(label);
            vertical.setComponentAlignment(label, Alignment.TOP_CENTER);
        }
    }
}
