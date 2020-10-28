package com.haulmont.view;

import com.vaadin.ui.*;

/**
 * Label Dialog with common Label
 * @author r.merdeev
 */
public class AbstractLabelDialog extends AbstractDialog { 
    /** Label */
    protected Label label;
    
    /** 
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public AbstractLabelDialog(String caption, TabInterface tab) {
        super(caption, tab);       
    }
    
    /**
     * Makes afterinstantiation actions
     * @param text text to put in label
     * @return self Dialog
     */
    public AbstractDialog create(String text) {
        label(text);
        return super.create();
    }
    
    @Override
    public AbstractDialog create() {
        label("");
        return super.create();
    }
    
    @Override
    protected void ok() {
        close();
    }
    
    /**
     * Adds label in vertical layout
     * @param text text of label
     */
    protected void label(String text) {   
        label = new Label(text);        
        vertical.addComponent(label);
        vertical.setComponentAlignment(label, Alignment.TOP_CENTER);
    }
}
