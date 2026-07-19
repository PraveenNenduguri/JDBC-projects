package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;
import com.model.Product;

public class ProductDAO {

	DBConnection db = new DBConnection();

	public void insertProduct(Product product) {

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

			ps.setString(1, product.getProductName());
			ps.setString(2, product.getCategory());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getStock());

			int rows = ps.executeUpdate();

			if (ResultUtil.checkResult(rows, 1)) {

				con.commit();
				System.out.println("Product Inserted Successfully.");

			} else {

				con.rollback();
				System.out.println("Insertion Failed. Transaction Rolled Back.");

			}

		} catch (Exception e) {

			try {

				if (con != null) {
					con.rollback();
					System.out.println("Transaction Rolled Back due to Exception.");
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



public void viewProducts() {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();

		String query = """
				SELECT *
				FROM products
				""";

		ps = con.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		System.out.println("\n========== Product List ==========");

		while (rs.next()) {

			System.out.println("Product ID   : " + rs.getInt("product_id"));
			System.out.println("Product Name : " + rs.getString("product_name"));
			System.out.println("Category     : " + rs.getString("category"));
			System.out.println("Price        : " + rs.getDouble("price"));
			System.out.println("Stock        : " + rs.getInt("stock"));
			System.out.println("----------------------------------");

		}

	} catch (Exception e) {

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

public void updateProductPrice(int productId, double newPrice) {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();
		con.setAutoCommit(false);

		String query = """
				UPDATE products
				SET price = ?
				WHERE product_id = ?
				""";

		ps = con.prepareStatement(query);

		ps.setDouble(1, newPrice);
		ps.setInt(2, productId);

		int rows = ps.executeUpdate();

		if (ResultUtil.checkResult(rows, 1)) {

			con.commit();
			System.out.println("Product Price Updated Successfully.");

		} else {

			con.rollback();
			System.out.println("Price Update Failed.");

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

public void deleteProduct(int productId) {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();
		con.setAutoCommit(false);

		String query = """
				DELETE FROM products
				WHERE product_id = ?
				""";

		ps = con.prepareStatement(query);

		ps.setInt(1, productId);

		int rows = ps.executeUpdate();

		if (ResultUtil.checkResult(rows, 1)) {

			con.commit();
			System.out.println("Product Deleted Successfully.");

		} else {

			con.rollback();
			System.out.println("Product Deletion Failed.");

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

public void searchByCategory(String category) {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {

		con = db.getConnection();

		String query = """
				SELECT *
				FROM products
				WHERE category = ?
				""";

		ps = con.prepareStatement(query);

		ps.setString(1, category);

		rs = ps.executeQuery();

		System.out.println("\n========= Products =========");

		boolean found = false;

		while (rs.next()) {

			found = true;

			System.out.println("Product ID   : " + rs.getInt("product_id"));
			System.out.println("Product Name : " + rs.getString("product_name"));
			System.out.println("Category     : " + rs.getString("category"));
			System.out.println("Price        : " + rs.getDouble("price"));
			System.out.println("Stock        : " + rs.getInt("stock"));
			System.out.println("----------------------------");

		}

		if (!found) {

			System.out.println("No Products Found.");

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

public void updateStock(int productId, int stock) {

	Connection con = null;
	PreparedStatement ps = null;

	try {

		con = db.getConnection();
		con.setAutoCommit(false);

		String query = """
				UPDATE products
				SET stock = ?
				WHERE product_id = ?
				""";

		ps = con.prepareStatement(query);

		ps.setInt(1, stock);
		ps.setInt(2, productId);

		int rows = ps.executeUpdate();

		if (ResultUtil.checkResult(rows, 1)) {

			con.commit();
			System.out.println("Product Stock Updated Successfully.");

		} else {

			con.rollback();
			System.out.println("Product Stock Update Failed.");

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