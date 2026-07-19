package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/PayrollManagementSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection con;

    // Private constructor
    private DBConnection() {
    }

    public static Connection getConnection() {

        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully...");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver Not Found.");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }

        return con;
    }

    public static void closeConnection() {

        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database Connection Closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}