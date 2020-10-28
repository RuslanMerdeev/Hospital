package com.haulmont.view;

import com.vaadin.ui.*;

/**
 * Class for Human Dialog with common FirstName, MiddleName and
 * LastName text fields
 * @author r.merdeev
 */
public abstract class AbstractHumanDialog extends AbstractCancelDialog {
    /** FirstName text field */
    protected final TextField firstName = new TextField("First Name");
    
    /** MiddleName text field */
    protected final TextField middleName = new TextField("Middle Name");
    
    /** LastName text field */
    protected final TextField lastName = new TextField("Last Name");
    
    /** 
     * Constructor
     * @param caption Dialog caption
     * @param tab Tan Interface
     */
    public AbstractHumanDialog(String caption, TabInterface tab) {
        super(caption, tab);
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
        vertical.addComponents(firstName, middleName, lastName);
        vertical.setComponentAlignment(firstName, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(middleName, Alignment.TOP_CENTER);
        vertical.setComponentAlignment(lastName, Alignment.TOP_CENTER);
    }
}
