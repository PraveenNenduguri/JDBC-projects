package com.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.dao.EmployeeDAO;
import com.model.Employee;

public class EmployeeService {

    Scanner sc = new Scanner(System.in);
    EmployeeDAO employeeDAO = new EmployeeDAO();

    // Add Employee
    public void addEmployee() {

        Employee emp = new Employee();

        System.out.print("Enter Employee ID : ");
        emp.setEmployeeId(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Employee Name : ");
        emp.setEmployeeName(sc.nextLine());

        System.out.print("Enter Department : ");
        emp.setDepartment(sc.nextLine());

        System.out.print("Enter Designation : ");
        emp.setDesignation(sc.nextLine());

        System.out.print("Enter Basic Salary : ");
        emp.setBasicSalary(sc.nextDouble());

        sc.nextLine();

        System.out.print("Enter Email : ");
        emp.setEmail(sc.nextLine());

        if (employeeDAO.addEmployee(emp))
            System.out.println("Employee Added Successfully.");
        else
            System.out.println("Failed to Add Employee.");
    }

    // Update Employee
    public void updateEmployee() {

        Employee emp = new Employee();

        System.out.print("Enter Employee ID : ");
        emp.setEmployeeId(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Employee Name : ");
        emp.setEmployeeName(sc.nextLine());

        System.out.print("Enter Department : ");
        emp.setDepartment(sc.nextLine());

        System.out.print("Enter Designation : ");
        emp.setDesignation(sc.nextLine());

        System.out.print("Enter Basic Salary : ");
        emp.setBasicSalary(sc.nextDouble());

        sc.nextLine();

        System.out.print("Enter Email : ");
        emp.setEmail(sc.nextLine());

        if (employeeDAO.updateEmployee(emp))
            System.out.println("Employee Updated Successfully.");
        else
            System.out.println("Employee Not Found.");
    }

    // Delete Employee
    public void deleteEmployee() {

        System.out.print("Enter Employee ID : ");
        int id = sc.nextInt();

        if (employeeDAO.deleteEmployee(id))
            System.out.println("Employee Deleted Successfully.");
        else
            System.out.println("Employee Not Found.");
    }

    // Search Employee
    public void searchEmployee() {

        System.out.print("Enter Employee ID : ");
        int id = sc.nextInt();

        Employee emp = employeeDAO.searchEmployee(id);

        if (emp != null)
            System.out.println(emp);
        else
            System.out.println("Employee Not Found.");
    }

    // Display All Employees
    public void displayEmployees() {

        ArrayList<Employee> list = employeeDAO.getAllEmployees();

        if (list.isEmpty()) {
            System.out.println("No Employees Found.");
        } else {

            System.out.println("\n========== Employee Details ==========");

            for (Employee emp : list) {
                System.out.println(emp);
            }
        }
    }
}