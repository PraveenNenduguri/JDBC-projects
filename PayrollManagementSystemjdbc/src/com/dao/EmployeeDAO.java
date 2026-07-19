package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.connection.DBConnection;
import com.model.Employee;

public class EmployeeDAO {

    private Connection con = DBConnection.getConnection();

    // Add Employee
    public boolean addEmployee(Employee emp) {

        String query = "INSERT INTO employees VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, emp.getEmployeeId());
            ps.setString(2, emp.getEmployeeName());
            ps.setString(3, emp.getDepartment());
            ps.setString(4, emp.getDesignation());
            ps.setDouble(5, emp.getBasicSalary());
            ps.setString(6, emp.getEmail());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Update Employee
    public boolean updateEmployee(Employee emp) {

        String query = "UPDATE employees SET employee_name=?, department=?, designation=?, basic_salary=?, email=? WHERE employee_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getDepartment());
            ps.setString(3, emp.getDesignation());
            ps.setDouble(4, emp.getBasicSalary());
            ps.setString(5, emp.getEmail());
            ps.setInt(6, emp.getEmployeeId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Delete Employee
    public boolean deleteEmployee(int employeeId) {

        String query = "DELETE FROM employees WHERE employee_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employeeId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Search Employee
    public Employee searchEmployee(int employeeId) {

        String query = "SELECT * FROM employees WHERE employee_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setEmployeeName(rs.getString("employee_name"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                emp.setEmail(rs.getString("email"));

                return emp;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Display All Employees
    public ArrayList<Employee> getAllEmployees() {

        ArrayList<Employee> list = new ArrayList<>();

        String query = "SELECT * FROM employees";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setEmployeeName(rs.getString("employee_name"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                emp.setEmail(rs.getString("email"));

                list.add(emp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}