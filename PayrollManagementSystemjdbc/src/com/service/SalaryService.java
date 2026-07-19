package com.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.dao.SalaryDAO;
import com.model.Salary;

public class SalaryService {

    Scanner sc = new Scanner(System.in);
    SalaryDAO salaryDAO = new SalaryDAO();

    // Add Salary
    public void addSalary() {

        Salary salary = new Salary();

        System.out.print("Enter Salary ID : ");
        salary.setSalaryId(sc.nextInt());

        System.out.print("Enter Employee ID : ");
        salary.setEmployeeId(sc.nextInt());

        System.out.print("Enter HRA : ");
        salary.setHra(sc.nextDouble());

        System.out.print("Enter DA : ");
        salary.setDa(sc.nextDouble());

        System.out.print("Enter Bonus : ");
        salary.setBonus(sc.nextDouble());

        System.out.print("Enter Deductions : ");
        salary.setDeductions(sc.nextDouble());

        double grossSalary = salary.getHra() + salary.getDa()
                + salary.getBonus();

        double netSalary = grossSalary - salary.getDeductions();

        salary.setGrossSalary(grossSalary);
        salary.setNetSalary(netSalary);

        if (salaryDAO.addSalary(salary))
            System.out.println("Salary Added Successfully.");
        else
            System.out.println("Failed to Add Salary.");
    }

    // Update Salary
    public void updateSalary() {

        Salary salary = new Salary();

        System.out.print("Enter Salary ID : ");
        salary.setSalaryId(sc.nextInt());

        System.out.print("Enter HRA : ");
        salary.setHra(sc.nextDouble());

        System.out.print("Enter DA : ");
        salary.setDa(sc.nextDouble());

        System.out.print("Enter Bonus : ");
        salary.setBonus(sc.nextDouble());

        System.out.print("Enter Deductions : ");
        salary.setDeductions(sc.nextDouble());

        double grossSalary = salary.getHra() + salary.getDa()
                + salary.getBonus();

        double netSalary = grossSalary - salary.getDeductions();

        salary.setGrossSalary(grossSalary);
        salary.setNetSalary(netSalary);

        if (salaryDAO.updateSalary(salary))
            System.out.println("Salary Updated Successfully.");
        else
            System.out.println("Salary Record Not Found.");
    }

    // Search Salary
    public void searchSalary() {

        System.out.print("Enter Salary ID : ");
        int salaryId = sc.nextInt();

        Salary salary = salaryDAO.searchSalary(salaryId);

        if (salary != null)
            System.out.println(salary);
        else
            System.out.println("Salary Record Not Found.");
    }

    // Display All Salaries
    public void displaySalaries() {

        ArrayList<Salary> list = salaryDAO.getAllSalaries();

        if (list.isEmpty()) {
            System.out.println("No Salary Records Found.");
        } else {

            System.out.println("\n========== Salary Details ==========");

            for (Salary salary : list) {
                System.out.println(salary);
            }
        }
    }

    // Total Salary
    public void totalSalary() {
        System.out.println("Total Salary Paid : "
                + salaryDAO.getTotalSalary());
    }

    // Average Salary
    public void averageSalary() {
        System.out.println("Average Salary : "
                + salaryDAO.getAverageSalary());
    }

    // Maximum Salary
    public void maximumSalary() {
        System.out.println("Maximum Salary : "
                + salaryDAO.getMaximumSalary());
    }

    // Minimum Salary
    public void minimumSalary() {
        System.out.println("Minimum Salary : "
                + salaryDAO.getMinimumSalary());
    }
}