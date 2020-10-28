package com.haulmont.view;

import com.haulmont.db.*;
import com.vaadin.ui.*;
import com.haulmont.entities.*;

/**
 * Class for Doctor Tab
 * @author r.merdeev
 */
public class DoctorTab extends AbstractHumanTab <Doctor> { 
    /** Statistic button */
    private final Button statistic = new Button("Statistic");
    
    /**
     * Constructor
     * @param ui UI
     * @param controller Abstract controller
     * @param caption Tab caption
     */
    public DoctorTab(UI ui, AbstractController <Doctor, Long> controller, String caption) {
        super(ui, controller, caption);        
    }
    
    @Override
    protected void addDialog(String caption) {
        show(new DoctorAddDialog(caption, this).create());
    }
    
    @Override
    protected boolean editDialog(String caption) {
        if (super.editDialog(caption)) show(new DoctorEditDialog(caption, this).create());
        return true;
    }
    
    @Override
    protected final void grid() {
        super.grid();
        grid.addColumn(Doctor::getSpeciality).setCaption("Speciality");
    }
    
    @Override
    protected void buttons() {
        super.buttons();
        statistic.addClickListener(event -> statisticDialog("Statistic for " + caption));        
        horizontal.addComponent(statistic);
    }
    
    /**
     * Creates Statistic Dialog
     * @param caption Dialog caption
     */
    private void statisticDialog(String caption) {
        if (get() != null) show(new StatisticDialog(caption, this, findReceipt).create());
        else show(new ErrorDialog().create("Select item first"));
    }
}
