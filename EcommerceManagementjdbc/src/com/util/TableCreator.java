package com.util;

import java.sql.Connection;
import java.sql.Statement;

import com.connection.DBConnection;

public class TableCreator {

    public void createTables() {

        DBConnection db = new DBConnection();
        Connection con = null;
        Statement st = null;

        try {

            con = db.getConnection();
            st = con.createStatement();

            String products = """
                    CREATE TABLE IF NOT EXISTS products(
                        product_id INT PRIMARY KEY AUTO_INCREMENT,
                        product_name VARCHAR(50) NOT NULL,
                        category VARCHAR(30),
                        price DECIMAL(10,2) CHECK(price > 0),
                        stock INT CHECK(stock >= 0)
                    )
                    """;

            String customers = """
                    CREATE TABLE IF NOT EXISTS customers(
                        customer_id INT PRIMARY KEY AUTO_INCREMENT,
                        customer_name VARCHAR(50) NOT NULL,
                        email VARCHAR(50) UNIQUE,
                        phone VARCHAR(15) UNIQUE
                    )
                    """;

            String orders = """
                    CREATE TABLE IF NOT EXISTS orders(
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        customer_id INT,
                        order_date DATE,
                        total_amount DECIMAL(10,2),
                        FOREIGN KEY(customer_id)
                        REFERENCES customers(customer_id)
                    )
                    """;

            String orderItems = """
                    CREATE TABLE IF NOT EXISTS order_items(
                        item_id INT PRIMARY KEY AUTO_INCREMENT,
                        order_id INT,
                        product_id INT,
                        quantity INT CHECK(quantity > 0),
                        FOREIGN KEY(order_id)
                        REFERENCES orders(order_id),
                        FOREIGN KEY(product_id)
                        REFERENCES products(product_id)
                    )
                    """;

            st.executeUpdate(products);
            st.executeUpdate(customers);
            st.executeUpdate(orders);
            st.executeUpdate(orderItems);

            System.out.println("=================================");
            System.out.println("Tables Created Successfully");
            System.out.println("=================================");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {

                if (st != null)
                    st.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}