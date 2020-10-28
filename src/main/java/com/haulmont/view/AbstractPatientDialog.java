package com.haulmont.view;

import com.vaadin.ui.*;

/**
 * Class for Patient Dialog with common Phone text field
 * @author r.merdeev
 */
public abstract class AbstractPatientDialog extends AbstractHumanDialog {
    /** Phone text field */
    protected final TextField phone = new TextField("Phone");
    
    /** 
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public AbstractPatientDialog(String caption, TabInterface tab) {
        super(caption, tab);      
    }
    
    @Override
    protected void fields() {
        super.fields();
        vertical.addComponent(phone);
        vertical.setComponentAlignment(phone, Alignment.TOP_CENTER);
    }
}
