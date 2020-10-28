package com.haulmont.view;

import com.haulmont.db.*;
import com.vaadin.ui.*;
import com.haulmont.entities.*;

/**
 * Class for Human Tab with common Find Interface
 * @author r.merdeev
 * @param <E> Entity for tab to work with
 */
public abstract class AbstractHumanTab <E extends AbstractHuman> extends AbstractTab <E> { 
    /** Receipt Find Interface */
    protected FindInterface findReceipt;
    
    /**
     * Constructor
     * @param ui UI
     * @param controller Abstract controller
     * @param caption Tab caption
     */
    public AbstractHumanTab (UI ui, AbstractController <E, Long> controller, String caption) {  
        super(ui, controller, caption);
    }
    
    /**
     * Makes afterinstantiation actions
     * @param findReceipt Receipt Find Interface
     * @return self Tab
     */
    public AbstractTab create(FindInterface findReceipt) {       
        this.findReceipt = findReceipt;
        return super.create();
    }
    
    @Override
    protected void deleteDialog(String caption) {
        if (findReceipt.find(get()).isEmpty()) super.deleteDialog(caption);
        else show(new ErrorDialog().create("Delete all receipts first"));
    }
    
    @Override
    protected void grid() {
        super.grid();
        grid.addColumn(AbstractHuman::getFirstName).setCaption("FirstName");
        grid.addColumn(AbstractHuman::getMiddleName).setCaption("MiddleName");
        grid.addColumn(AbstractHuman::getLastName).setCaption("LastName");
    }
}
