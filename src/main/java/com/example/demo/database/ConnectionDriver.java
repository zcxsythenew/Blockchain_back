package com.example.demo.database;

import com.example.demo.constants.DatabaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDriver {
    private static ConnectionDriver driver;
    private Connection connection;

    private ConnectionDriver() {
        try {
            Class.forName(DatabaseConstants.JDBC_DRIVER);
            connection = DriverManager.getConnection(DatabaseConstants.DB_URL, DatabaseConstants.USER, DatabaseConstants.PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ConnectionDriver getInstance() {
        if (driver == null) {
            driver = new ConnectionDriver();
        }

        return driver;
    }

    public Connection getConnection() {
        return connection;
    }
}
