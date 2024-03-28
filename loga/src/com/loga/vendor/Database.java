package com.loga.vendor;

import com.loga.ui.AlertError;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
    private Connection connection;
    
    public Database() {
        final String DRIVER = "org.h2.Driver";
        final String JDBC_URL = "jdbc:h2:./resources/loga";
        final String USERNAME = "loga";
        final String PASSWORD = "Log@gmc+";

        /*final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        final String JDBC_URL = "jdbc:sqlserver://gmcplus.org\\SQLSERVERGMCPLUS;DatabaseName=loga;integratedSecurity=false;";
        final String USERNAME = "loga";
        final String PASSWORD = "Log@gmc+";*/

        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Echec connexion");
            AlertError.getInstance().setContentText("Echec lors de la connexion à la base de données :" + e.getMessage());
            AlertError.getInstance().showAndWait();
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}