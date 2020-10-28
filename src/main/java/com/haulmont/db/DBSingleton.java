package com.haulmont.db;

import com.haulmont.entities.*;
import com.haulmont.utils.*;
import java.sql.*;

/**
 * Class for DB
 * @author r.merdeev
 */
public class DBSingleton {
    /** Singleton DB */
    private static DBSingleton instance = null;
    
    /** DB connection */
    private Connection connection;

    /**
     * Constructor
     */
    private DBSingleton(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:db", "SA", "");
            init();
        } catch (SQLException | ClassNotFoundException e) {
            Log.exception("DB", e);
        }
    }

    /**
     * Returns created or existed DB 
     * @return DB
     */
    public static DBSingleton getInstance(){

        if (instance == null) {
            instance = new DBSingleton();            
        }
        return instance;
    }
    
    /**
     * Sends SQL query
     * @param sql query
     * @return result set
     * @throws SQLException 
     */
    public ResultSet query(String sql) throws SQLException {
        Statement statement;
        statement = createStatement();
        ResultSet rs = statement.executeQuery(sql);
        statement.close();
        return rs;
    }
    
    /**
     * Returns created statement
     * @return statement
     */
    private Statement createStatement() {
        {
            try {
                if (connection == null)
                    return null;
                else
                    return connection.createStatement();
            } catch (SQLException e) {
                Log.exception("DB", e);
                return null;
            }
        }
    }
    
    /**
     * Initialize DB
     */
    private void init() {
        try {
            query("CREATE TABLE doctors (\n" +
                    "    id BIGINT IDENTITY PRIMARY KEY,\n" +
                    "    firstname VARCHAR(" + AbstractHuman.FIRST_NAME_LEN + ") NOT NULL,\n" +
                    "    middlename VARCHAR(" + AbstractHuman.MIDDLE_NAME_LEN +") NOT NULL,\n" +
                    "    lastname VARCHAR(" + AbstractHuman.LAST_NAME_LEN + ") NOT NULL,\n" +
                    "    speciality VARCHAR(" + Doctor.SPECIALITY_LEN + ")\n" +
                    ");"
            );
            query("INSERT INTO doctors (firstname, middlename, lastname, speciality)\n" +
                    "values\n" +
                    "    ('Vasiliy', 'Petrovich', 'Kemkin', 'onko'),\n" +
                    "    ('Semen', 'Viktorovich', 'Sudakov', 'neural'),\n" +
                    "    ('Vlad', 'Ivanovich','Shevchenko', 'surgerer')\n" +
                    "    ;"
            );
            
            query("CREATE TABLE patients (\n" +
                    "    id BIGINT IDENTITY PRIMARY KEY,\n" +
                    "    firstname VARCHAR(" + AbstractHuman.FIRST_NAME_LEN + ") NOT NULL,\n" +
                    "    middlename VARCHAR(" + AbstractHuman.MIDDLE_NAME_LEN +") NOT NULL,\n" +
                    "    lastname VARCHAR(" + AbstractHuman.LAST_NAME_LEN + ") NOT NULL,\n" +
                    "    phone VARCHAR(" + Patient.PHONE_LEN + ")\n" +
                    ");"
            );
            query("INSERT INTO patients (firstname, middlename, lastname, phone)\n" +
                    "values\n" +
                    "    ('Ruslan', 'Zamirovich', 'Merdeev', '+71111111111'),\n" +
                    "    ('Alexey', 'D.', 'Nikolayev', '+72222222222'),\n" +
                    "    ('Maxim', 'Igorevich','Kornev', '+73333333333')\n" +
                    "    ;"
            );
            
            query("CREATE TABLE receipts (\n" +
                    "    id BIGINT IDENTITY PRIMARY KEY,\n" +
                    "    description VARCHAR(" + Receipt.DESCRIPTION_LEN + "),\n" +
                    "    doctorID BIGINT NOT NULL,\n" +
                    "    patientID BIGINT NOT NULL,\n" +
                    "    creationDate DATE NOT NULL,\n" +
                    "    validity INTEGER NOT NULL,\n" +
                    "    priority INTEGER,\n" +
                    "    FOREIGN KEY (doctorID) REFERENCES doctors(id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (patientID) REFERENCES patients(id) ON DELETE CASCADE\n" +
                    ");"
            );
            query("INSERT INTO receipts (description, doctorID, patientID, creationDate, validity, priority)\n" +
                    "values\n" +
                    "    ('use that very useful drug', 0, 2, DATE '2020-10-23', 2, 0),\n" +
                    "    ('it is cool to use that drug', 0, 1, DATE '2020-10-25', 4, 1),\n" +
                    "    ('drug drug drug', 1, 0, DATE '2020-10-27', 8, 2)\n" +
                    "    ;"
            );
        }
        catch (SQLException e) {
            Log.exception("DB", e);
        }
    }
}