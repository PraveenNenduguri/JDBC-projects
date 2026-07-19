package com.model;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String department;
    private String designation;
    private double basicSalary;
    private String email;

    // Default Constructor
    public Employee() {
    }

    // Parameterized Constructor
    public Employee(int employeeId, String employeeName, String department,
                    String designation, double basicSalary, String email) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.email = email;
    }

    // Getters and Setters

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", basicSalary=" + basicSalary +
                ", email='" + email + '\'' +
                '}';
    }
}