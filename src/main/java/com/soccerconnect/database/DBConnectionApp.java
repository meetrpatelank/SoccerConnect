package com.soccerconnect.database;

import com.soccerconnect.utils.ConfigReaderInterface;

import java.sql.*;

public class DBConnectionApp {

    // App to connect to DB

    String HOST;
    String SCHEMA;
    String USER;
    String PASSWORD;
    String URL;
    
    private Connection conn;
    private static DBConnectionApp db;

    private DBConnectionApp(ConfigReaderInterface configReader) {
        try {
            HOST = configReader.getConfigValue("HOST");
            SCHEMA = configReader.getConfigValue("SCHEMA");
            USER = configReader.getConfigValue("USER");
            PASSWORD = configReader.getConfigValue("PASSWORD");
            URL = "jdbc:mysql://" + HOST + ":3306/" + SCHEMA;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Usage of singleton pattern
    public static DBConnectionApp getDbApp(ConfigReaderInterface configReader){
        if(db==null){
            db = new DBConnectionApp(configReader);
        }
        return db;
    }

    public Connection getConnection(){
        // Method to get DB connection object
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(){
        // Method to close connection
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
