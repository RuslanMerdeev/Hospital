package com.haulmont.view;

import com.vaadin.ui.*;

/**
 * Class for Abstract Dialog with common Tab Interface and Ok button
 * @author r.merdeev
 */
public abstract class AbstractDialog extends Window {
    /** Tab Intreface */
    protected final TabInterface tab;
    
    /** Vertical layout */
    protected final VerticalLayout vertical = new VerticalLayout();
    
    /** Horizontal layout */
    protected final Layout horizontal = new HorizontalLayout();
    
    /** OK button */
    protected final Button ok = new Button("OK");
    
    /** 
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     */
    public AbstractDialog(String caption, TabInterface tab) {
        super(caption);
        this.tab = tab;          
    }
    
    /**
     * Makes afterinstantiation actions
     * @return self Dialog
     */
    public AbstractDialog create() {
        buttons();
        setModal(true);
        setResizable(false);
        setClosable(false);
        setContent(vertical);
        return this;
    }

    /** Actions on OK button */
    protected abstract void ok();
    
    /**
     * Adds buttons to horizontal layout
     */
    protected void buttons() {
        horizontal.addComponent(ok);
        vertical.addComponents(horizontal);
        vertical.setComponentAlignment(horizontal, Alignment.TOP_CENTER);
        ok.addClickListener(event -> ok());
    }
}
