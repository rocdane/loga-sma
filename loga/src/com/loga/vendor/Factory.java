package com.loga.vendor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Factory
{
    private static Factory instance;

    private Database ds;
    
    private Factory() {
        this.ds = new Database();
    }

    public static Factory getInstance(){
        if(instance==null)
            instance = new Factory();
        return instance;
    }

    public ResultSet get(String queryStmt) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        try {
            rs = this.ds.getConnection().createStatement().executeQuery(queryStmt);
        }
        catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : \n" + e.getMessage());
            throw e;
        }
        return rs;
    }
    
    public void set(String sqlStmt) throws SQLException, ClassNotFoundException {
        try {
            this.ds.getConnection().createStatement().executeUpdate(sqlStmt);
        }
        catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : \n" + e.getMessage());
            throw e;
        }
    }
}
