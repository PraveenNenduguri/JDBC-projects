package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.connection.DBConnection;
import com.model.Payroll;

public class PayrollDAO {

    private Connection con = DBConnection.getConnection();

    // Add Payroll
    public boolean addPayroll(Payroll payroll) {

        String query = "INSERT INTO payroll VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, payroll.getPayrollId());
            ps.setInt(2, payroll.getEmployeeId());
            ps.setString(3, payroll.getMonth());
            ps.setInt(4, payroll.getYear());
            ps.setString(5, payroll.getPaymentDate());
            ps.setString(6, payroll.getSalaryStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Update Payroll
    public boolean updatePayroll(Payroll payroll) {

        String query = "UPDATE payroll SET month=?, year=?, payment_date=?, salary_status=? WHERE payroll_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, payroll.getMonth());
            ps.setInt(2, payroll.getYear());
            ps.setString(3, payroll.getPaymentDate());
            ps.setString(4, payroll.getSalaryStatus());
            ps.setInt(5, payroll.getPayrollId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Delete Payroll
    public boolean deletePayroll(int payrollId) {

        String query = "DELETE FROM payroll WHERE payroll_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, payrollId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // Search Payroll
    public Payroll searchPayroll(int payrollId) {

        String query = "SELECT * FROM payroll WHERE payroll_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, payrollId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setMonth(rs.getString("month"));
                payroll.setYear(rs.getInt("year"));
                payroll.setPaymentDate(rs.getString("payment_date"));
                payroll.setSalaryStatus(rs.getString("salary_status"));

                return payroll;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Display All Payroll Records
    public ArrayList<Payroll> getAllPayrolls() {

        ArrayList<Payroll> list = new ArrayList<>();

        String query = "SELECT * FROM payroll";

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setMonth(rs.getString("month"));
                payroll.setYear(rs.getInt("year"));
                payroll.setPaymentDate(rs.getString("payment_date"));
                payroll.setSalaryStatus(rs.getString("salary_status"));

                list.add(payroll);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    // Batch Processing
    public void addPayrollBatch(ArrayList<Payroll> payrollList) {

        String query = "INSERT INTO payroll VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(query);

            for (Payroll payroll : payrollList) {

                ps.setInt(1, payroll.getPayrollId());
                ps.setInt(2, payroll.getEmployeeId());
                ps.setString(3, payroll.getMonth());
                ps.setInt(4, payroll.getYear());
                ps.setString(5, payroll.getPaymentDate());
                ps.setString(6, payroll.getSalaryStatus());

                ps.addBatch();
            }

            ps.executeBatch();

            System.out.println("Batch Processing Completed Successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}