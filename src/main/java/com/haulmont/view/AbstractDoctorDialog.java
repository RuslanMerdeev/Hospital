package com.haulmont.view;

import com.vaadin.ui.*;

/** 
 * Class for Doctor Dialog with common Speciality text field
 * @author r.merdeev
 */
public abstract class AbstractDoctorDialog extends AbstractHumanDialog {
    /** Speciality text field */
    protected final TextField speciality = new TextField("Speciality");
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Intreface
     */
    public AbstractDoctorDialog(String caption, TabInterface tab) {
        super(caption, tab);
    }
    
    @Override
    protected void fields() {
        super.fields();
        vertical.addComponent(speciality);
        vertical.setComponentAlignment(speciality, Alignment.TOP_CENTER);
    }
}
