package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;
import com.model.Customer;

public class CustomerDAO {

	DBConnection db = new DBConnection();

	public void insertCustomer(Customer customer) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = db.getConnection();
			con.setAutoCommit(false);

			String query = """
					INSERT INTO customers
					(customer_name, email, phone)
					VALUES (?, ?, ?)
					""";

			ps = con.prepareStatement(query);

			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());

			int rows = ps.executeUpdate();

			if (ResultUtil.checkResult(rows, 1)) {

				con.commit();
				System.out.println("Customer Inserted Successfully.");

			} else {

				con.rollback();
				System.out.println("Customer Insertion Failed.");

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



public void viewCustomers() {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {

		con = db.getConnection();

		String query = """
				SELECT *
				FROM customers
				""";

		ps = con.prepareStatement(query);

		rs = ps.executeQuery();

		System.out.println("\n========== Customer Details ==========");

		while (rs.next()) {

			System.out.println("Customer ID   : " + rs.getInt("customer_id"));
			System.out.println("Customer Name : " + rs.getString("customer_name"));
			System.out.println("Email         : " + rs.getString("email"));
			System.out.println("Phone         : " + rs.getString("phone"));
			System.out.println("--------------------------------------");

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

public void updateCustomer(int customerId, String email, String phone) {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();
		con.setAutoCommit(false);

		String query = """
				UPDATE customers
				SET email = ?, phone = ?
				WHERE customer_id = ?
				""";

		ps = con.prepareStatement(query);

		ps.setString(1, email);
		ps.setString(2, phone);
		ps.setInt(3, customerId);

		int rows = ps.executeUpdate();

		if (ResultUtil.checkResult(rows, 1)) {

			con.commit();
			System.out.println("Customer Updated Successfully.");

		} else {

			con.rollback();
			System.out.println("Customer Update Failed.");

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

public void deleteCustomer(int customerId) {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();
		con.setAutoCommit(false);

		String query = """
				DELETE FROM customers
				WHERE customer_id = ?
				""";

		ps = con.prepareStatement(query);

		ps.setInt(1, customerId);

		int rows = ps.executeUpdate();

		if (ResultUtil.checkResult(rows, 1)) {

			con.commit();
			System.out.println("Customer Deleted Successfully.");

		} else {

			con.rollback();
			System.out.println("Customer Deletion Failed.");

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
}