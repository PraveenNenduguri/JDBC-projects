package com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LeaveManagement {

	private Connection con;
	private Scanner sc;

	public LeaveManagement() {

		con = DBConnection.getConnection();
		sc = new Scanner(System.in);

	}

	// ================= CREATE TABLES =================

	public void createTables() {

		try {

			con.setAutoCommit(false);

			Statement stmt = con.createStatement();

			String employeeTable = """
					CREATE TABLE IF NOT EXISTS employees(
					    employee_id INT PRIMARY KEY AUTO_INCREMENT,
					    employee_name VARCHAR(100) NOT NULL,
					    department VARCHAR(50) NOT NULL,
					    email VARCHAR(100) UNIQUE NOT NULL,
					    phone VARCHAR(15) UNIQUE NOT NULL
					)
					""";

			String leaveBalanceTable = """
					CREATE TABLE IF NOT EXISTS leave_balance(
					    employee_id INT PRIMARY KEY,
					    total_leaves INT DEFAULT 20,
					    used_leaves INT DEFAULT 0,
					    remaining_leaves INT DEFAULT 20,
					    FOREIGN KEY(employee_id)
					    REFERENCES employees(employee_id)
					    ON DELETE CASCADE
					)
					""";

			String leaveRequestTable = """
					CREATE TABLE IF NOT EXISTS leave_requests(
					    leave_id INT PRIMARY KEY AUTO_INCREMENT,
					    employee_id INT,
					    from_date DATE NOT NULL,
					    to_date DATE NOT NULL,
					    leave_days INT NOT NULL,
					    reason VARCHAR(200),
					    status VARCHAR(20) DEFAULT 'Pending',
					    FOREIGN KEY(employee_id)
					    REFERENCES employees(employee_id)
					    ON DELETE CASCADE
					)
					""";

			stmt.execute(employeeTable);
			stmt.execute(leaveBalanceTable);
			stmt.execute(leaveRequestTable);

			con.commit();

			System.out.println("----------------------------------------");
			System.out.println("Tables Created Successfully");
			System.out.println("----------------------------------------");

			stmt.close();

		} catch (SQLException e) {

			try {

				con.rollback();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}

	// ================= VALIDATIONS =================

	public boolean isValidName(String name) {

		return name != null && name.matches("[A-Za-z ]{3,30}");

	}

	public boolean isValidEmail(String email) {

		return Pattern.matches(
				"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
				email);

	}

	public boolean isValidPhone(String phone) {

		return phone.matches("[6-9][0-9]{9}");

	}

	public boolean isValidDepartment(String department) {

		return department.equalsIgnoreCase("IT")
				|| department.equalsIgnoreCase("HR")
				|| department.equalsIgnoreCase("Finance")
				|| department.equalsIgnoreCase("Testing");

	}

	public long calculateLeaveDays(LocalDate fromDate,
			LocalDate toDate) {

		return ChronoUnit.DAYS.between(fromDate, toDate) + 1;

	}

	// ================= REGISTER EMPLOYEE =================

	public void registerEmployee() {

		try {

			System.out.println("\n========== Employee Registration ==========");

			System.out.print("Enter Employee Name : ");
			String name = sc.nextLine();

			if (!isValidName(name)) {

				System.out.println("Invalid Name.");
				return;

			}

			System.out.print("Enter Department (IT/HR/Finance/Testing) : ");
			String department = sc.nextLine();

			if (!isValidDepartment(department)) {

				System.out.println("Invalid Department.");
				return;

			}

			System.out.print("Enter Email : ");
			String email = sc.nextLine();

			if (!isValidEmail(email)) {

				System.out.println("Invalid Email.");
				return;

			}

			System.out.print("Enter Phone Number : ");
			String phone = sc.nextLine();

			if (!isValidPhone(phone)) {

				System.out.println("Invalid Phone Number.");
				return;

			}

			con.setAutoCommit(false);

			String employeeSql = """
					INSERT INTO employees
					(employee_name,department,email,phone)
					VALUES(?,?,?,?)
					""";

			PreparedStatement psEmp = con.prepareStatement(
					employeeSql,
					Statement.RETURN_GENERATED_KEYS);

			psEmp.setString(1, name);
			psEmp.setString(2, department);
			psEmp.setString(3, email);
			psEmp.setString(4, phone);

			int empRows = psEmp.executeUpdate();

			if (empRows != 1) {

				con.rollback();
				System.out.println("Employee Registration Failed.");
				return;

			}

			ResultSet rs = psEmp.getGeneratedKeys();

			int employeeId = 0;

			if (rs.next()) {

				employeeId = rs.getInt(1);

			}

			String balanceSql = """
					INSERT INTO leave_balance
					(employee_id,total_leaves,used_leaves,remaining_leaves)
					VALUES(?,?,?,?)
					""";

			PreparedStatement psBalance = con.prepareStatement(balanceSql);

			psBalance.setInt(1, employeeId);
			psBalance.setInt(2, 20);
			psBalance.setInt(3, 0);
			psBalance.setInt(4, 20);

			int balanceRows = psBalance.executeUpdate();

			if (balanceRows != 1) {

				con.rollback();
				System.out.println("Leave Balance Creation Failed.");
				return;

			}

			con.commit();

			System.out.println("Employee Registered Successfully.");
			System.out.println("Employee ID : " + employeeId);

			rs.close();
			psEmp.close();
			psBalance.close();

		}

		catch (SQLException e) {

			try {

				con.rollback();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}


	// ================= VIEW EMPLOYEES =================

	public void viewEmployees() {

		try {

			String sql = "SELECT * FROM employees";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n================ EMPLOYEES ================");

			while (rs.next()) {

				Employee emp = new Employee(
						rs.getInt("employee_id"),
						rs.getString("employee_name"),
						rs.getString("department"),
						rs.getString("email"),
						rs.getString("phone"));

				System.out.println(emp);
				System.out.println("----------------------------------------");

			}

			rs.close();
			stmt.close();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

	}


	// ================= SEARCH EMPLOYEE =================

	public void searchEmployee() {

		try {

			System.out.print("Enter Employee ID : ");

			int id = sc.nextInt();
			sc.nextLine();

			String sql = """
					SELECT *
					FROM employees
					WHERE employee_id=?
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Employee emp = new Employee(
						rs.getInt("employee_id"),
						rs.getString("employee_name"),
						rs.getString("department"),
						rs.getString("email"),
						rs.getString("phone"));

				System.out.println(emp);

			}

			else {

				System.out.println("Employee Not Found.");

			}

			rs.close();
			ps.close();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

	}


	// ================= UPDATE EMPLOYEE =================

	public void updateEmployee() {

		try {

			System.out.print("Enter Employee ID : ");

			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter New Email : ");

			String email = sc.nextLine();

			if (!isValidEmail(email)) {

				System.out.println("Invalid Email.");
				return;

			}

			con.setAutoCommit(false);

			String sql = """
					UPDATE employees
					SET email=?
					WHERE employee_id=?
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setInt(2, id);

			int rows = ps.executeUpdate();

			if (rows == 1) {

				con.commit();
				System.out.println("Employee Updated Successfully.");

			}

			else {

				con.rollback();
				System.out.println("Employee Not Found.");

			}

			ps.close();

		}

		catch (SQLException e) {

			try {

				con.rollback();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}


	// ================= DELETE EMPLOYEE =================

	public void deleteEmployee() {

		try {

			System.out.print("Enter Employee ID : ");

			int id = sc.nextInt();

			con.setAutoCommit(false);

			String sql = """
					DELETE FROM employees
					WHERE employee_id=?
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			if (rows == 1) {

				con.commit();
				System.out.println("Employee Deleted Successfully.");

			}

			else {

				con.rollback();
				System.out.println("Employee Not Found.");

			}

			ps.close();

		}

		catch (SQLException e) {

			try {

				con.rollback();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}

	// ================= APPLY LEAVE =================

	public void applyLeave() {

		try {

			System.out.print("Enter Employee ID : ");
			int employeeId = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter From Date (yyyy-MM-dd) : ");
			LocalDate fromDate = LocalDate.parse(sc.nextLine());

			System.out.print("Enter To Date (yyyy-MM-dd) : ");
			LocalDate toDate = LocalDate.parse(sc.nextLine());

			if (toDate.isBefore(fromDate)) {

				System.out.println("Invalid Date Range.");
				return;

			}

			int leaveDays = (int) calculateLeaveDays(fromDate, toDate);

			String checkSql = """
					SELECT remaining_leaves
					FROM leave_balance
					WHERE employee_id = ?
					""";

			PreparedStatement psCheck = con.prepareStatement(checkSql);
			psCheck.setInt(1, employeeId);

			ResultSet rs = psCheck.executeQuery();

			if (!rs.next()) {

				System.out.println("Employee Not Found.");
				return;

			}

			int remaining = rs.getInt("remaining_leaves");

			if (leaveDays > remaining) {

				System.out.println("Insufficient Leave Balance.");
				return;

			}

			System.out.print("Enter Reason : ");
			String reason = sc.nextLine();

			con.setAutoCommit(false);

			String sql = """
					INSERT INTO leave_requests
					(employee_id,from_date,to_date,leave_days,reason,status)
					VALUES(?,?,?,?,?,?)
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, employeeId);
			ps.setDate(2, Date.valueOf(fromDate));
			ps.setDate(3, Date.valueOf(toDate));
			ps.setInt(4, leaveDays);
			ps.setString(5, reason);
			ps.setString(6, "Pending");

			int rows = ps.executeUpdate();

			if (rows == 1) {

				con.commit();
				System.out.println("Leave Applied Successfully.");

			} else {

				con.rollback();
				System.out.println("Leave Application Failed.");

			}

			rs.close();
			ps.close();
			psCheck.close();

		}

		catch (Exception e) {

			try {

				con.rollback();

			}

			catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}


	// ================= VIEW LEAVE REQUESTS =================

	public void viewLeaveRequests() {

		try {

			String sql = """
					SELECT *
					FROM leave_requests
					""";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n========== LEAVE REQUESTS ==========");

			while (rs.next()) {

				System.out.println("--------------------------------");

				System.out.println("Leave ID      : " + rs.getInt("leave_id"));
				System.out.println("Employee ID   : " + rs.getInt("employee_id"));
				System.out.println("From Date     : " + rs.getDate("from_date"));
				System.out.println("To Date       : " + rs.getDate("to_date"));
				System.out.println("Leave Days    : " + rs.getInt("leave_days"));
				System.out.println("Reason        : " + rs.getString("reason"));
				System.out.println("Status        : " + rs.getString("status"));

			}

			rs.close();
			stmt.close();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

	}


	// ================= APPROVE LEAVE =================

	public void approveLeave() {

		try {

			System.out.print("Enter Leave ID : ");

			int leaveId = sc.nextInt();

			con.setAutoCommit(false);

			String selectSql = """
					SELECT employee_id,leave_days
					FROM leave_requests
					WHERE leave_id=?
					AND status='Pending'
					""";

			PreparedStatement psSelect = con.prepareStatement(selectSql);

			psSelect.setInt(1, leaveId);

			ResultSet rs = psSelect.executeQuery();

			if (!rs.next()) {

				System.out.println("Leave Request Not Found.");
				con.rollback();
				return;

			}

			int employeeId = rs.getInt("employee_id");
			int leaveDays = rs.getInt("leave_days");

			String updateRequest = """
					UPDATE leave_requests
					SET status='Approved'
					WHERE leave_id=?
					""";

			PreparedStatement ps1 = con.prepareStatement(updateRequest);

			ps1.setInt(1, leaveId);

			int row1 = ps1.executeUpdate();

			String updateBalance = """
					UPDATE leave_balance
					SET used_leaves=used_leaves+?,
					    remaining_leaves=remaining_leaves-?
					WHERE employee_id=?
					""";

			PreparedStatement ps2 = con.prepareStatement(updateBalance);

			ps2.setInt(1, leaveDays);
			ps2.setInt(2, leaveDays);
			ps2.setInt(3, employeeId);

			int row2 = ps2.executeUpdate();

			if (row1 == 1 && row2 == 1) {

				con.commit();

				System.out.println("Leave Approved Successfully.");

			} else {

				con.rollback();

				System.out.println("Approval Failed.");

			}

			rs.close();
			psSelect.close();
			ps1.close();
			ps2.close();

		}

		catch (SQLException e) {

			try {

				con.rollback();

			}

			catch (SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}

	// ================= REJECT LEAVE =================

	public void rejectLeave() {

		try {

			System.out.print("Enter Leave ID : ");
			int leaveId = sc.nextInt();

			con.setAutoCommit(false);

			String sql = """
					UPDATE leave_requests
					SET status='Rejected'
					WHERE leave_id=?
					AND status='Pending'
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, leaveId);

			int rows = ps.executeUpdate();

			if(rows == 1) {

				con.commit();

				System.out.println("Leave Rejected Successfully.");

			}

			else {

				con.rollback();

				System.out.println("Leave Request Not Found.");

			}

			ps.close();

		}

		catch(SQLException e) {

			try {

				con.rollback();

			}

			catch(SQLException ex) {

				ex.printStackTrace();

			}

			e.printStackTrace();

		}

	}

	// ================= VIEW LEAVE BALANCE =================

	public void viewLeaveBalance() {

		try {

			System.out.print("Enter Employee ID : ");

			int employeeId = sc.nextInt();

			String sql = """
					SELECT *
					FROM leave_balance
					WHERE employee_id=?
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, employeeId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				System.out.println("\n========== LEAVE BALANCE ==========");

				System.out.println("Employee ID      : " + employeeId);
				System.out.println("Total Leaves     : " + rs.getInt("total_leaves"));
				System.out.println("Used Leaves      : " + rs.getInt("used_leaves"));
				System.out.println("Remaining Leaves : " + rs.getInt("remaining_leaves"));

			}

			else {

				System.out.println("Employee Not Found.");

			}

			rs.close();
			ps.close();

		}

		catch(SQLException e) {

			e.printStackTrace();

		}

	}

	public void lowLeaveEmployees() {

		try {

			String sql = """
					SELECT e.employee_id,
					       e.employee_name,
					       l.remaining_leaves
					FROM employees e
					JOIN leave_balance l
					ON e.employee_id=l.employee_id
					WHERE l.remaining_leaves<5
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				System.out.println(rs.getInt(1)+"  "
						+rs.getString(2)+"  "
						+rs.getInt(3));

			}

			rs.close();
			ps.close();

		}

		catch(SQLException e) {

			e.printStackTrace();

		}

	}

	public void approvedLeaves() {

		try {

			String sql = """
					SELECT *
					FROM leave_requests
					WHERE status='Approved'
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				System.out.println("--------------------------------");
				System.out.println("Leave ID : "+rs.getInt("leave_id"));
				System.out.println("Employee ID : "+rs.getInt("employee_id"));
				System.out.println("Days : "+rs.getInt("leave_days"));

			}

			rs.close();
			ps.close();

		}

		catch(SQLException e) {

			e.printStackTrace();

		}

	}


	public void searchDepartment() {

		try {

			sc.nextLine();

			System.out.print("Enter Department : ");

			String dept=sc.nextLine();

			String sql="""
					SELECT *
					FROM employees
					WHERE department=?
					""";

			PreparedStatement ps=con.prepareStatement(sql);

			ps.setString(1, dept);

			ResultSet rs=ps.executeQuery();

			while(rs.next()) {

				System.out.println("--------------------------------");

				System.out.println(rs.getInt("employee_id"));

				System.out.println(rs.getString("employee_name"));

				System.out.println(rs.getString("department"));

			}

			rs.close();
			ps.close();

		}

		catch(SQLException e) {

			e.printStackTrace();

		}

	}
	public void employeeWithMaximumLeave() {

		try {

			String sql="""
					SELECT e.employee_name,
					       l.used_leaves
					FROM employees e
					JOIN leave_balance l
					ON e.employee_id=l.employee_id
					ORDER BY l.used_leaves DESC
					LIMIT 1
					""";

			PreparedStatement ps=con.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();

			if(rs.next()) {

				System.out.println("Employee Name : "
						+rs.getString(1));

				System.out.println("Leaves Used : "
						+rs.getInt(2));

			}

			rs.close();
			ps.close();

		}

		catch(SQLException e) {

			e.printStackTrace();

		}

	}
}