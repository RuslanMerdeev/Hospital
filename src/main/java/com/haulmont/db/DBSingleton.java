package com.haulmont.db;

import com.haulmont.utils.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
     * Returns created statement
     * @return statement
     */
    public Statement createStatement() {
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
}