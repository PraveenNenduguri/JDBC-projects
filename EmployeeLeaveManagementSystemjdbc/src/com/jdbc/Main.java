package com.jdbc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LeaveManagement lm = new LeaveManagement();

        lm.createTables();

        int choice;

        do {

            System.out.println("\n=================================================");
            System.out.println("      EMPLOYEE LEAVE MANAGEMENT SYSTEM");
            System.out.println("=================================================");
            System.out.println("1. Register Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Apply Leave");
            System.out.println("7. View Leave Requests");
            System.out.println("8. Approve Leave");
            System.out.println("9. Reject Leave");
            System.out.println("10. View Leave Balance");
            System.out.println("11. Employees with Low Leave Balance");
            System.out.println("12. View Approved Leaves");
            System.out.println("13. Search Employees by Department");
            System.out.println("14. Employee with Maximum Leaves");
            System.out.println("15. Exit");
            System.out.println("=================================================");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    lm.registerEmployee();
                    break;

                case 2:
                    lm.viewEmployees();
                    break;

                case 3:
                    lm.searchEmployee();
                    break;

                case 4:
                    lm.updateEmployee();
                    break;

                case 5:
                    lm.deleteEmployee();
                    break;

                case 6:
                    lm.applyLeave();
                    break;

                case 7:
                    lm.viewLeaveRequests();
                    break;

                case 8:
                    lm.approveLeave();
                    break;

                case 9:
                    lm.rejectLeave();
                    break;

                case 10:
                    lm.viewLeaveBalance();
                    break;

                case 11:
                    lm.lowLeaveEmployees();
                    break;

                case 12:
                    lm.approvedLeaves();
                    break;

                case 13:
                    lm.searchDepartment();
                    break;

                case 14:
                    lm.employeeWithMaximumLeave();
                    break;

                case 15:

                    DBConnection.closeConnection();
                    sc.close();

                    System.out.println("\nThank You...!");
                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        } while (true);

    }

}