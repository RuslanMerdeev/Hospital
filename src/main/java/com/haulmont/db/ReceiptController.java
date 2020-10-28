package com.haulmont.db;

import java.util.*;
import com.haulmont.entities.*;
import com.haulmont.utils.Log;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Class for Receipt DB controller
 * @author r.merdeev
 */
public class ReceiptController extends AbstractController <Receipt, Long> {    
    /** Key interface for DoctorController */
    private final KeyInterface doctorController;
    
    /** Key interface for PatientController */
    private final KeyInterface patientController;
    
    /**
     * Constructor
     * @param doctorController Key interface for DoctorController
     * @param patientController Key interface for PatientController
     */
    public ReceiptController(KeyInterface doctorController, KeyInterface patientController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
    }
    
    @Override
    public List<Receipt> getAll() {
        try {
            return getList(sendQuery("SELECT * FROM receipts"));
        } catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public Receipt getByKey(Long key) {
        try {
            return getList(sendQuery("SELECT * FROM receipts WHERE id = " + key)).get(0);
        } catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return null;
        }
    }
    
    @Override
    public boolean delete(Long key) {
        try {
            sendQuery("DELETE FROM receipts WHERE id = " + key);
            return true;
        } catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return false;
        }
    }
    
    @Override
    public boolean update(Receipt entity) {
        try {
            sendQuery("UPDATE receipts SET "
                    + "description = '" + entity.getDescription()
                    + "', doctorID = " + entity.getDoctor().getKey()
                    + ", patientID = " + entity.getPatient().getKey()
                    + ", creationDate = DATE '" + entity.getStringCreationDate()
                    + "', validity = " + entity.getValidity()
                    + ", priority = " + entity.getPriority().get()
                    + " WHERE id = " + entity.getKey()
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return false;
        }
    }
    
    @Override
    public boolean create(Receipt entity) {
        try {
            sendQuery("INSERT INTO receipts (description, doctorID, patientID, creationDate, validity, priority)" +
                    " values ('"
                    + entity.getDescription() + "', "
                    + entity.getDoctor().getKey() + ", "
                    + entity.getPatient().getKey() + ", DATE '"
                    + entity.getStringCreationDate() + "', "
                    + entity.getValidity() + ", "
                    + entity.getPriority().get() + ")"
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return false;
        }
    }
    
    /**
     * Returns List of Receipts for Human
     * @param human human
     * @param list list to get from
     * @return list of Receipts
     */
    public List<Receipt> getForHuman(AbstractHuman human, List<Receipt> list) {
        if (human == null) return list;
        String str;
        if (human.getClass() == Doctor.class) {
            str = "SELECT * FROM receipts WHERE doctorID = " + human.getKey();
        }
        else if (human.getClass() == Patient.class) {
            str = "SELECT * FROM receipts WHERE patientID = " + human.getKey();
        }
        else return new ArrayList<>();
        str = forList(str, list);
        try {
            return getList(sendQuery(str));
        }
        catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return new ArrayList<>();
        }
    }
       
    /**
     * Returns List of Receipts for Priority
     * @param entity priority
     * @param list list to get from
     * @return list of Receipts
     */
    public List<Receipt> getForPriority(Priority entity, List<Receipt> list) {
        if (entity == null) return list;
        String str = "SELECT * FROM receipts WHERE " +
                    "priority = " + entity.get();
        str = forList(str, list);
        try {
            return getList(sendQuery(str));
        }
        catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Returns List of Receipts contained description
     * @param description description
     * @param list list to get from
     * @return list of Receipts
     */
    public List<Receipt> getForDescription(String description, List<Receipt> list) {
        if (description.isEmpty()) return list;
        String str = "SELECT * FROM receipts WHERE " +
                    "description LIKE '%" + description + "%'";
        str = forList(str, list);
        try {
            return getList(sendQuery(str));
        }
        catch (SQLException e) {
            Log.exception("Receipt controller", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    protected List<Receipt> getList(ResultSet rs) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        while (rs.next()){
            try {
                list.add( new Receipt(
                    rs.getLong(1),
                    rs.getString(2),
                    (Doctor)doctorController.getByKey(rs.getLong(3)),
                    (Patient)patientController.getByKey(rs.getLong(4)),
                    rs.getDate(5),
                    rs.getInt(6),
                    new Priority((byte)rs.getInt(7)))
            );
            }
            catch (InstantiationException e) {
                Log.exception("Receipt controller", e);
            }
        }
        return list;
    }
       
    /**
     * Adds select parameters from list to get from
     * @param s input string
     * @param list list to get from
     * @return string with parameters
     */
    private String forList (String s, List<Receipt> list) {
        String str = s;
        if (list != null && !list.isEmpty()) { 
            str = str + " AND ( ";
            for (Receipt r : list) {
                str = str + "id = " + r.getKey() + " OR ";
            }
            str = str.substring(0, str.length()-3);
            str = str + ")";
        }
        Log.message("R", str);
        return str;
    }
}