package com.haulmont.db;

import java.util.*;
import com.haulmont.entities.*;
import com.haulmont.utils.Log;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Class for Patient DB controller
 * @author r.merdeev
 */
public class PatientController extends AbstractController <Patient, Long> {
    
    @Override
    public List<Patient> getAll() {       
        try {
            return getList(sendQuery("SELECT * FROM patients"));
        } catch (SQLException e) {
            Log.exception("Patient controller", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public Patient getByKey(Long key) {
        try {
            return getList(sendQuery("SELECT * FROM patients WHERE id = " + key)).get(0);
        } catch (SQLException e) {
            Log.exception("Patient controller", e);
            return null;
        }
    }
    
    @Override
    public boolean delete(Long key) {
        try {
            sendQuery("DELETE FROM patients WHERE id = " + key);
            return true;
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return false;
        }
    }
    
    @Override
    public boolean update(Patient entity) {
        try {
            sendQuery("UPDATE patients SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', middlename = '" + entity.getMiddleName()
                    + "', lastname = '" + entity.getLastName()
                    + "', phone = '" + entity.getPhone()
                    + "' WHERE id = " + entity.getKey()
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Patient controller", e);
            return false;
        }
    }
    
    @Override
    public boolean create(Patient entity) {
        try {
            sendQuery("INSERT INTO patients (firstname, middlename, lastname, phone)" +
                    " values ('"
                    + entity.getFirstName() + "', '"
                    + entity.getMiddleName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getPhone() + "')"
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Patient controller", e);
            return false;
        }
    }
    
    @Override
    protected List<Patient> getList(ResultSet rs) throws SQLException {
        List<Patient> list = new ArrayList<>();
        while (rs.next()){
            try {
                list.add( new Patient(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
                );
            }
            catch (InstantiationException e) {
                Log.exception("Patient controller", e);
            }
        }
        return list;
    }
}