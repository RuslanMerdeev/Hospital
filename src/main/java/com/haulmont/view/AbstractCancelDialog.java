package com.haulmont.view;

import com.vaadin.ui.*;

/**
 * Class for Cancel Dialog with common Cancel button
 * @author r.merdeev
 */
public abstract class AbstractCancelDialog extends AbstractDialog {
    /** Cancel button */
    protected final Button cancel = new Button("CANCEL");
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public AbstractCancelDialog(String caption, TabInterface tab) {
        super(caption, tab);         
    }
    
    @Override
    protected final void buttons() {
        super.buttons();
        horizontal.addComponent(cancel);
        vertical.addComponents(horizontal);
        vertical.setComponentAlignment(horizontal, Alignment.TOP_CENTER);
        cancel.addClickListener(event -> close()); 
    }
}
