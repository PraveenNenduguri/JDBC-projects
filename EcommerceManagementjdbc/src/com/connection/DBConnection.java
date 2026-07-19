package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3307/EcommerceManagementjdbc";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}