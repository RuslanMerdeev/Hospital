package com.haulmont.view;

import com.haulmont.db.*;
import com.vaadin.ui.*;
import com.haulmont.entities.*;
import java.util.List;

/**
 * Class for Receipt Tab
 * @author r.merdeev
 */
public class ReceiptTab extends AbstractTab <Receipt> implements FindInterface { 
    /** Doctor Select Interface */
    protected final SelectInterface selectedDoctor;
    
    /** Patient Select Interface */
    protected final SelectInterface selectedPatient;
    
    /** Second horizontal layout for filters */
    private final Layout horizontal0 = new HorizontalLayout();
    
    /** Description for filter */
    private final TextField description = new TextField("Description");
    
    /** Priority for filter */
    private final NativeSelect<Priority> priority = new NativeSelect<>("Priority");
    
    /** Button for filter */
    private final Button filter = new Button("Filter");
    
    /** Button for filter for Patient */
    private final Button filterForPatient = new Button("Filter for patient");
    
    /**
     * Constructor
     * @param ui UI
     * @param controller Abstract controller
     * @param caption Tab caption
     * @param selectedDoctor Doctor Select Interface
     * @param selectedPatient Patient Select Interface
     */
    public ReceiptTab(UI ui, AbstractController <Receipt, Long> controller, String caption, SelectInterface selectedDoctor, SelectInterface selectedPatient) {
        super(ui, controller, caption);
        this.selectedDoctor = selectedDoctor;
        this.selectedPatient = selectedPatient;
    }
    
    @Override
    public AbstractTab create() {  
        filters();
        return super.create();
    }
    
    @Override
    public List<Receipt> find (AbstractEntity entity) {
        return (((ReceiptController)controller).getForHuman((AbstractHuman)entity, null));
    }
    
    @Override
    protected void buttons() {
        super.buttons();
        filter.addClickListener(event -> filter(null));       
        filterForPatient.addClickListener(event -> filterForPatient());         
        horizontal.addComponents(filter, filterForPatient);
    }
    
    @Override
    protected void addDialog(String caption) {
        if (check()) show(new ReceiptAddDialog(caption, this, selectedDoctor, selectedPatient).create());
    }
    
    @Override
    protected boolean editDialog(String caption) {
        if (super.editDialog(caption)) show(new ReceiptEditDialog(caption, this).create());           
        return true;
    }
    
    @Override
    protected final void grid() {
        super.grid();
        grid.addColumn(Receipt::getDescription).setCaption("Description");        
        grid.addColumn(r -> r.getDoctor().toString()).setCaption("Doctor");
        grid.addColumn(r -> r.getPatient().toString()).setCaption("Patient");
        grid.addColumn(Receipt::getStringCreationDate).setCaption("Creation Date");
        grid.addColumn(Receipt::getValidity).setCaption("Validity");
        grid.addColumn(r -> r.getPriority().toString()).setCaption("Priority");
    }
    
    /**
     * Checks selected Doctor and Patient for Add dialog
     * @return result
     */
    private boolean check() {
        if ((selectedDoctor.get() != null && selectedPatient.get() != null)) return true;
        show(new ErrorDialog().create("Select doctor and patient first"));
        return false;
    }
    
    /**
     * Adds filter components to second horizontal layout
     */
    private void filters() {
        priority.setItems(Priority.getValues());        
        horizontal0.addComponents(description, priority);
        this.addComponent(horizontal0); 
    }
    
    /** Makes filtration by description, priority and patient */
    private void filter(Patient patient) {
        ReceiptController ctrl = (ReceiptController)controller;
        Priority prior = priority.getSelectedItem().isPresent() ? priority.getSelectedItem().get() : null;
        grid.setItems(ctrl.getForHuman(patient, ctrl.getForDescription(description.getValue(), ctrl.getForPriority(prior, ctrl.getAll()))));
    }
    
    /** Makes filtration for selected Patient */
    private void filterForPatient() {
        filter((Patient)selectedPatient.get());
    }
}
