package com.haulmont.db;

import java.util.*;
import com.haulmont.entities.*;
import com.haulmont.utils.Log;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Class for Doctor DB controller
 * @author r.merdeev
 */
public class DoctorController extends AbstractController <Doctor, Long> {
    @Override
    public List<Doctor> getAll() {
        try {
            return getList(sendQuery("SELECT * FROM doctors"));
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public Doctor getByKey(Long key) {
        try {
            return getList(sendQuery("SELECT * FROM doctors WHERE id = " + key)).get(0);
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return null;
        }
    }
    
    @Override
    public boolean delete(Long key) {
        try {
            sendQuery("DELETE FROM doctors WHERE id = " + key);
            return true;
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return false;
        }
    }
    
    @Override
    public boolean update(Doctor entity) {
        try {
            sendQuery("UPDATE doctors SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', middlename = '" + entity.getMiddleName()
                    + "', lastname = '" + entity.getLastName()
                    + "', speciality = '" + entity.getSpeciality()
                    + "' WHERE id = " + entity.getKey()
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return false;
        }
    }
    
    @Override
    public boolean create(Doctor entity) {
        try {
            sendQuery("INSERT INTO doctors (firstname, middlename, lastname, speciality)" +
                    " values ('"
                    + entity.getFirstName() + "', '"
                    + entity.getMiddleName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getSpeciality() + "')"
            );
            return true;
        } catch (SQLException e) {
            Log.exception("Doctor controller", e);
            return false;
        }
    }
    
    @Override
    protected List<Doctor> getList(ResultSet rs) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        while (rs.next()){
            try {
                list.add( new Doctor(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
                );
            }
            catch (InstantiationException e) {
                Log.exception("Doctor controller", e);
            }
        }
        return list;
    }
}