package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.connection.DBConnection;
import com.model.Orders;
import com.model.Product;

public class OrderDAO {

    DBConnection db = new DBConnection();

    public void placeOrder(Orders order) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = db.getConnection();
            con.setAutoCommit(false);

            String query = """
                    INSERT INTO orders
                    (customer_id, order_date, total_amount)
                    VALUES (?, ?, ?)
                    """;

            ps = con.prepareStatement(query);

            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getOrderDate());
            ps.setDouble(3, order.getTotalAmount());

            int rows = ps.executeUpdate();

            if (ResultUtil.checkResult(rows, 1)) {

                con.commit();
                System.out.println("Order Placed Successfully.");

            } else {

                con.rollback();
                System.out.println("Order Placement Failed.");

            }

        } catch (Exception e) {

            try {

                if (con != null) {

                    con.rollback();
                    System.out.println("Transaction Rolled Back.");

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        } finally {

            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }



public void viewOrders() {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        con = db.getConnection();

        String query = """
                SELECT *
                FROM orders
                ORDER BY order_id
                """;

        ps = con.prepareStatement(query);

        rs = ps.executeQuery();

        System.out.println("\n========== Order Details ==========");

        while (rs.next()) {

            System.out.println("Order ID      : " + rs.getInt("order_id"));
            System.out.println("Customer ID   : " + rs.getInt("customer_id"));
            System.out.println("Order Date    : " + rs.getDate("order_date"));
            System.out.println("Total Amount  : " + rs.getDouble("total_amount"));
            System.out.println("-----------------------------------");

        }

    } catch (Exception e) {

        e.printStackTrace();

    } finally {

        try {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();

            if (con != null)
                con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

public void cancelOrder(int orderId) {

    Connection con = null;
    PreparedStatement ps = null;

    try {

        con = db.getConnection();
        con.setAutoCommit(false);

        String query = """
                DELETE FROM orders
                WHERE order_id = ?
                """;

        ps = con.prepareStatement(query);

        ps.setInt(1, orderId);

        int rows = ps.executeUpdate();

        if (ResultUtil.checkResult(rows, 1)) {

            con.commit();
            System.out.println("Order Cancelled Successfully.");

        } else {

            con.rollback();
            System.out.println("Order Cancellation Failed.");

        }

    } catch (Exception e) {

        try {

            if (con != null) {

                con.rollback();
                System.out.println("Transaction Rolled Back.");

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        e.printStackTrace();

    } finally {

        try {

            if (ps != null)
                ps.close();

            if (con != null)
                con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

public void batchInsertProducts(List<Product> products) {

    Connection con = null;
    PreparedStatement ps = null;

    try {

        con = db.getConnection();
        con.setAutoCommit(false);

        String query = """
                INSERT INTO products
                (product_name, category, price, stock)
                VALUES (?, ?, ?, ?)
                """;

        ps = con.prepareStatement(query);

        for (Product product : products) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            ps.addBatch();

        }

        int[] rows = ps.executeBatch();

        if (ResultUtil.checkResult(rows.length, products.size())) {

            con.commit();
            System.out.println("Batch Insert Completed Successfully.");

        } else {

            con.rollback();
            System.out.println("Batch Insert Failed.");

        }

    } catch (Exception e) {

        try {

            if (con != null) {

                con.rollback();
                System.out.println("Transaction Rolled Back.");

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        e.printStackTrace();

    } finally {

        try {

            if (ps != null)
                ps.close();

            if (con != null)
                con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

public void orderTracking(int orderId) {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        con = db.getConnection();

        String query = """
                SELECT o.order_id,
                       c.customer_name,
                       p.product_name,
                       oi.quantity,
                       o.order_date,
                       o.total_amount
                FROM orders o
                INNER JOIN customers c
                    ON o.customer_id = c.customer_id
                INNER JOIN order_items oi
                    ON o.order_id = oi.order_id
                INNER JOIN products p
                    ON oi.product_id = p.product_id
                WHERE o.order_id = ?
                """;

        ps = con.prepareStatement(query);

        ps.setInt(1, orderId);

        rs = ps.executeQuery();

        boolean found = false;

        while (rs.next()) {

            found = true;

            System.out.println("\n========== Order Tracking ==========");

            System.out.println("Order ID      : " + rs.getInt("order_id"));
            System.out.println("Customer Name : " + rs.getString("customer_name"));
            System.out.println("Product Name  : " + rs.getString("product_name"));
            System.out.println("Quantity      : " + rs.getInt("quantity"));
            System.out.println("Order Date    : " + rs.getDate("order_date"));
            System.out.println("Total Amount  : " + rs.getDouble("total_amount"));
            System.out.println("------------------------------------");

        }

        if (!found) {

            System.out.println("Order Not Found.");

        }

    } catch (Exception e) {

        e.printStackTrace();

    } finally {

        try {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();

            if (con != null)
                con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

public void viewCustomerOrders() {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        con = db.getConnection();

        String query = """
                SELECT o.order_id,
                       c.customer_name,
                       o.order_date,
                       o.total_amount
                FROM customers c
                INNER JOIN orders o
                ON c.customer_id = o.customer_id
                ORDER BY o.order_id
                """;

        ps = con.prepareStatement(query);

        rs = ps.executeQuery();

        System.out.println("\n========= Customer Orders =========");

        while (rs.next()) {

            System.out.println("Order ID      : " + rs.getInt("order_id"));
            System.out.println("Customer Name : " + rs.getString("customer_name"));
            System.out.println("Order Date    : " + rs.getDate("order_date"));
            System.out.println("Total Amount  : " + rs.getDouble("total_amount"));
            System.out.println("----------------------------------");

        }

    } catch (Exception e) {

        e.printStackTrace();

    } finally {

        try {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();

            if (con != null)
                con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
}