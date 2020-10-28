package com.haulmont.view;

import com.vaadin.ui.*;
import com.haulmont.db.*;
import com.haulmont.entities.*;

/**
 * Class for Abstract Tab with common interface (Add, Edit, Delete buttons; grid)
 * @author r.merdeev
 * @param <E> Entity for tab to work with
 */
public abstract class AbstractTab <E extends AbstractEntity> extends VerticalLayout implements TabInterface {
    /** UI */
    protected final UI ui;
    
    /** Controller to control Entity table */
    protected AbstractController <E, Long> controller;
    
    /** Horizontal Layout for buttons */
    protected final Layout horizontal = new HorizontalLayout();
    
    /** Add button */
    protected final Button add = new Button("Add");
    
    /** Edit button */
    protected final Button edit = new Button("Edit");
    
    /** Delete button */
    protected final Button delete = new Button("Delete");
    
    /** Grid */
    protected final Grid<E> grid = new Grid<>();
    
    /** Tab caption */
    protected final String caption;
    
    /**
     * Constructor
     * @param ui UI
     * @param controller Abstract Controler
     * @param caption Tab caption
     */
    public AbstractTab(UI ui, AbstractController <E, Long> controller, String caption) {  
        this.ui = ui;
        this.controller = controller;
        this.caption = caption;
    }
    
    /**
     * Makes afterinstantiation actions
     * @return self Tab
     */
    public AbstractTab create() {
        buttons();
        grid();
        this.setCaption(caption + "s"); 
        this.addComponent(horizontal);          
        this.addComponent(grid); 
        return this;
    }
       
    @Override
    public final AbstractEntity get() {
        if (grid == null) return null;
        if (grid.getSelectionModel().getFirstSelectedItem().isPresent()) {
            return (AbstractEntity)grid.getSelectionModel().getFirstSelectedItem().get();
        }
        return null;
    }
    
    @Override
    public final void update() {
        grid.setItems(controller.getAll());
    }
    
    @Override
    public final void add(AbstractEntity entity) {
        if (entity == null) return;
        controller.create((E)entity);
        update();
    }
    
    @Override
    public final void edit(AbstractEntity entity) {
        if (entity == null) return;
        controller.update((E)entity);
        update();
    }
    
    @Override
    public final void delete(AbstractEntity entity) {
        if (entity == null) return;
        controller.delete(entity.getKey());
        update();
    }
    
    @Override
    public final void show(AbstractDialog dialog) {
        ui.addWindow(dialog);
    }
    
    /**
     * Creates Add Entity dialog
     * @param caption Dialog caption
     */
    protected abstract void addDialog(String caption);
    
    /**
     * Creates Edit Entity dialog
     * @param caption Dialog caption
     * @return creation result
     */
    protected boolean editDialog(String caption) {
        if (get() != null) return true;
        show(new ErrorDialog().create("Select item first"));
        return false;
    }
    
    /**
     * Creates Delete Entity dialog
     * @param caption Dialog caption
     */
    protected void deleteDialog(String caption) {
        if (get() != null) show(new DeleteDialog(caption, this).create());
        else show(new ErrorDialog().create("Select item first"));
    }
    
    /**
     * Adds buttons to horizontal layout
     */
    protected void buttons() {
        add.addClickListener(event -> addDialog("Add new " + caption));

        edit.addClickListener(event -> editDialog("Edit " + caption));

        delete.addClickListener(event -> deleteDialog("Delete " + caption));
        
        horizontal.addComponents(add, edit, delete);
    }
    
    /**
     * Configures grid
     */
    protected void grid() {
        grid.setSizeFull();
        grid.setItems(controller.getAll());
    }
}
