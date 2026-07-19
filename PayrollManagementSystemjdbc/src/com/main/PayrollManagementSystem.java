package com.main;

import java.util.Scanner;

import com.connection.DBConnection;
import com.service.EmployeeService;
import com.service.PayrollService;
import com.service.SalaryService;

public class PayrollManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeService employeeService = new EmployeeService();
        SalaryService salaryService = new SalaryService();
        PayrollService payrollService = new PayrollService();

        int choice;

        do {

            System.out.println("\n========== Payroll Management System ==========");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Add Salary");
            System.out.println("7. Update Salary");
            System.out.println("8. Search Salary");
            System.out.println("9. Display All Salaries");
            System.out.println("10. Add Payroll");
            System.out.println("11. Update Payroll");
            System.out.println("12. Delete Payroll");
            System.out.println("13. Search Payroll");
            System.out.println("14. Display All Payroll Records");
            System.out.println("15. Total Salary");
            System.out.println("16. Average Salary");
            System.out.println("17. Maximum Salary");
            System.out.println("18. Minimum Salary");
            System.out.println("19. Batch Processing");
            System.out.println("20. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    employeeService.addEmployee();
                    break;

                case 2:
                    employeeService.updateEmployee();
                    break;

                case 3:
                    employeeService.deleteEmployee();
                    break;

                case 4:
                    employeeService.searchEmployee();
                    break;

                case 5:
                    employeeService.displayEmployees();
                    break;

                case 6:
                    salaryService.addSalary();
                    break;

                case 7:
                    salaryService.updateSalary();
                    break;

                case 8:
                    salaryService.searchSalary();
                    break;

                case 9:
                    salaryService.displaySalaries();
                    break;

                case 10:
                    payrollService.addPayroll();
                    break;

                case 11:
                    payrollService.updatePayroll();
                    break;

                case 12:
                    payrollService.deletePayroll();
                    break;

                case 13:
                    payrollService.searchPayroll();
                    break;

                case 14:
                    payrollService.displayPayrolls();
                    break;

                case 15:
                    salaryService.totalSalary();
                    break;

                case 16:
                    salaryService.averageSalary();
                    break;

                case 17:
                    salaryService.maximumSalary();
                    break;

                case 18:
                    salaryService.minimumSalary();
                    break;

                case 19:
                    payrollService.batchProcessing();
                    break;

                case 20:
                    DBConnection.closeConnection();
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 20);

        sc.close();
    }
}