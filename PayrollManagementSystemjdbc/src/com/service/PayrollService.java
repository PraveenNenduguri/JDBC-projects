package com.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.dao.PayrollDAO;
import com.model.Payroll;

public class PayrollService {

    Scanner sc = new Scanner(System.in);
    PayrollDAO payrollDAO = new PayrollDAO();

    // Add Payroll
    public void addPayroll() {

        Payroll payroll = new Payroll();

        System.out.print("Enter Payroll ID : ");
        payroll.setPayrollId(sc.nextInt());

        System.out.print("Enter Employee ID : ");
        payroll.setEmployeeId(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Month : ");
        payroll.setMonth(sc.nextLine());

        System.out.print("Enter Year : ");
        payroll.setYear(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Payment Date (YYYY-MM-DD) : ");
        payroll.setPaymentDate(sc.nextLine());

        System.out.print("Enter Salary Status (Paid/Pending) : ");
        payroll.setSalaryStatus(sc.nextLine());

        if (payrollDAO.addPayroll(payroll))
            System.out.println("Payroll Added Successfully.");
        else
            System.out.println("Failed to Add Payroll.");
    }

    // Update Payroll
    public void updatePayroll() {

        Payroll payroll = new Payroll();

        System.out.print("Enter Payroll ID : ");
        payroll.setPayrollId(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Month : ");
        payroll.setMonth(sc.nextLine());

        System.out.print("Enter Year : ");
        payroll.setYear(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Payment Date (YYYY-MM-DD) : ");
        payroll.setPaymentDate(sc.nextLine());

        System.out.print("Enter Salary Status (Paid/Pending) : ");
        payroll.setSalaryStatus(sc.nextLine());

        if (payrollDAO.updatePayroll(payroll))
            System.out.println("Payroll Updated Successfully.");
        else
            System.out.println("Payroll Record Not Found.");
    }

    // Delete Payroll
    public void deletePayroll() {

        System.out.print("Enter Payroll ID : ");
        int payrollId = sc.nextInt();

        if (payrollDAO.deletePayroll(payrollId))
            System.out.println("Payroll Deleted Successfully.");
        else
            System.out.println("Payroll Record Not Found.");
    }

    // Search Payroll
    public void searchPayroll() {

        System.out.print("Enter Payroll ID : ");
        int payrollId = sc.nextInt();

        Payroll payroll = payrollDAO.searchPayroll(payrollId);

        if (payroll != null)
            System.out.println(payroll);
        else
            System.out.println("Payroll Record Not Found.");
    }

    // Display All Payroll Records
    public void displayPayrolls() {

        ArrayList<Payroll> list = payrollDAO.getAllPayrolls();

        if (list.isEmpty()) {
            System.out.println("No Payroll Records Found.");
        } else {

            System.out.println("\n========== Payroll Details ==========");

            for (Payroll payroll : list) {
                System.out.println(payroll);
            }
        }
    }

    // Batch Processing
    public void batchProcessing() {

        ArrayList<Payroll> payrollList = new ArrayList<>();

        System.out.print("Enter Number of Payroll Records: ");
        int n = sc.nextInt();

        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter Details for Payroll " + i);

            Payroll payroll = new Payroll();

            System.out.print("Payroll ID : ");
            payroll.setPayrollId(sc.nextInt());

            System.out.print("Employee ID : ");
            payroll.setEmployeeId(sc.nextInt());

            sc.nextLine();

            System.out.print("Month : ");
            payroll.setMonth(sc.nextLine());

            System.out.print("Year : ");
            payroll.setYear(sc.nextInt());

            sc.nextLine();

            System.out.print("Payment Date (YYYY-MM-DD) : ");
            payroll.setPaymentDate(sc.nextLine());

            System.out.print("Salary Status : ");
            payroll.setSalaryStatus(sc.nextLine());

            payrollList.add(payroll);
        }

        payrollDAO.addPayrollBatch(payrollList);
    }
}