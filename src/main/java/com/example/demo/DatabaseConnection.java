package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "sql11477198";
        String databaseUser = "sql11477198";
        String databasePassword = "e6WF3cVHP7";
        String url = "jdbc:mysql://sql11.freemysqlhosting.net/" + databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}

