package com.haulmont.view;

import com.haulmont.db.*;
import com.vaadin.ui.*;
import com.haulmont.entities.*;

/**
 * Class for Patient Tab
 * @author r.merdeev
 */
public class PatientTab extends AbstractHumanTab <Patient> { 
    
    /**
     * Constructor
     * @param ui UI
     * @param controller Abstract controller
     * @param caption Tab caption
     */
    public PatientTab(UI ui, AbstractController <Patient, Long> controller, String caption) {
        super(ui, controller, caption);        
    }
    
    @Override
    protected void addDialog(String caption) {
        show(new PatientAddDialog(caption, this).create());
    }
    
    @Override
    protected boolean editDialog(String caption) {
        if (super.editDialog(caption)) show(new PatientEditDialog(caption, this).create());
        return true;
    }
    
    @Override
    protected final void grid() {
        super.grid();
        grid.addColumn(Patient::getPhone).setCaption("Phone");
    }
}
