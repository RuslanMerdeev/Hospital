package com.haulmont.testtask;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.haulmont.view.*;
import com.haulmont.db.*;

/**
 * Class for main UI
 * @author r.merdeev
 */
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    /** Sheet of tabs */
    private TabSheet tabSheet;
    
    @Override
    protected void init(VaadinRequest request) {        
        tabSheet = new TabSheet();
        PatientController patientController = new PatientController();
        DoctorController doctorController = new DoctorController();
        PatientTab patientTab = new PatientTab(this, patientController, "Patient");       
        DoctorTab doctorTab = new DoctorTab(this, doctorController, "Doctor");        
        ReceiptTab receiptTab = new ReceiptTab(this, new ReceiptController(doctorController, patientController), "Receipt", doctorTab, patientTab);
        patientTab.create(receiptTab);
        doctorTab.create(receiptTab);
        receiptTab.create();
        tabSheet.addTab(patientTab);
        tabSheet.addTab(doctorTab);
        tabSheet.addTab(receiptTab);
        tabSheet.setSizeFull();

        setContent(tabSheet);
        tabSheet.addSelectedTabChangeListener(event -> changed());
    }
    
    /**
     * Updates tab grid
     */
    private void changed() {
        ((UpdateInterface)tabSheet.getSelectedTab()).update();
    }
}