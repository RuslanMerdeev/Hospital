package com.haulmont.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/** 
 * Class for DB controller
 * @author r.merdeev
 * @param <E> DB entity
 * @param <K> DB key
 */
public abstract class AbstractController <E, K> implements KeyInterface <E, K> {   
    /** Singleton DB */
    private final DBSingleton db;

    /**
     * Constructor
     */
    AbstractController() {
        db = DBSingleton.getInstance();
        init();
    }
    
    /**
     * Returns all Entities controlled Table
     * @return list of entities
     */
    public abstract List<E> getAll();
    
    /**
     * Updates Entity
     * @param entity entity
     * @return result
     */
    public abstract boolean update(E entity);
    
    /**
     * Deletes Entity by key
     * @param key key
     * @return result
     */
    public abstract boolean delete(K key);
    
    /**
     * Creates Entity
     * @param entity entity
     * @return result
     */
    public abstract boolean create(E entity);
    
    /**
     * Sends SQL query
     * @param sql query
     * @return result set
     * @throws SQLException 
     */
    protected ResultSet sendQuery(String sql) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        statement.close();
        return rs;
    }
    
    /**
     * Initializes table and fills it with default values
     */
    protected void init() {}
    
    /**
     * Returns List of Entities from Result Set
     * @param rs Result Set
     * @return list of entities
     * @throws SQLException 
     */
    protected abstract List<E> getList(ResultSet rs) throws SQLException;
}