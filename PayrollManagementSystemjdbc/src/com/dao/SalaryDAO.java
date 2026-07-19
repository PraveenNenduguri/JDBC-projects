package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.connection.DBConnection;
import com.model.Salary;

public class SalaryDAO {

    private Connection con = DBConnection.getConnection();

    // Add Salary
    public boolean addSalary(Salary salary) {

        String query = "INSERT INTO salaries VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, salary.getSalaryId());
            ps.setInt(2, salary.getEmployeeId());
            ps.setDouble(3, salary.getHra());
            ps.setDouble(4, salary.getDa());
            ps.setDouble(5, salary.getBonus());
            ps.setDouble(6, salary.getDeductions());
            ps.setDouble(7, salary.getGrossSalary());
            ps.setDouble(8, salary.getNetSalary());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Update Salary
    public boolean updateSalary(Salary salary) {

        String query = "UPDATE salaries SET hra=?, da=?, bonus=?, deductions=?, gross_salary=?, net_salary=? WHERE salary_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, salary.getHra());
            ps.setDouble(2, salary.getDa());
            ps.setDouble(3, salary.getBonus());
            ps.setDouble(4, salary.getDeductions());
            ps.setDouble(5, salary.getGrossSalary());
            ps.setDouble(6, salary.getNetSalary());
            ps.setInt(7, salary.getSalaryId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Search Salary
    public Salary searchSalary(int salaryId) {

        String query = "SELECT * FROM salaries WHERE salary_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, salaryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Salary salary = new Salary();

                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setHra(rs.getDouble("hra"));
                salary.setDa(rs.getDouble("da"));
                salary.setBonus(rs.getDouble("bonus"));
                salary.setDeductions(rs.getDouble("deductions"));
                salary.setGrossSalary(rs.getDouble("gross_salary"));
                salary.setNetSalary(rs.getDouble("net_salary"));

                return salary;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Display All Salaries
    public ArrayList<Salary> getAllSalaries() {

        ArrayList<Salary> list = new ArrayList<>();

        String query = "SELECT * FROM salaries";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Salary salary = new Salary();

                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setHra(rs.getDouble("hra"));
                salary.setDa(rs.getDouble("da"));
                salary.setBonus(rs.getDouble("bonus"));
                salary.setDeductions(rs.getDouble("deductions"));
                salary.setGrossSalary(rs.getDouble("gross_salary"));
                salary.setNetSalary(rs.getDouble("net_salary"));

                list.add(salary);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    // Total Net Salary
    public double getTotalSalary() {

        String query = "SELECT SUM(net_salary) AS total FROM salaries";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    // Average Net Salary
    public double getAverageSalary() {

        String query = "SELECT AVG(net_salary) AS average FROM salaries";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return rs.getDouble("average");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    // Maximum Net Salary
    public double getMaximumSalary() {

        String query = "SELECT MAX(net_salary) AS maximum FROM salaries";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return rs.getDouble("maximum");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    // Minimum Net Salary
    public double getMinimumSalary() {

        String query = "SELECT MIN(net_salary) AS minimum FROM salaries";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return rs.getDouble("minimum");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}