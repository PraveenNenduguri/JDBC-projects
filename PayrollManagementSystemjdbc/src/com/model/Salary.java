package com.model;

public class Salary {

    private int salaryId;
    private int employeeId;
    private double hra;
    private double da;
    private double bonus;
    private double deductions;
    private double grossSalary;
    private double netSalary;

    // Default Constructor
    public Salary() {
    }

    // Parameterized Constructor
    public Salary(int salaryId, int employeeId, double hra, double da,
                  double bonus, double deductions,
                  double grossSalary, double netSalary) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.hra = hra;
        this.da = da;
        this.bonus = bonus;
        this.deductions = deductions;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
    }

    // Getters and Setters

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getDa() {
        return da;
    }

    public void setDa(double da) {
        this.da = da;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", employeeId=" + employeeId +
                ", hra=" + hra +
                ", da=" + da +
                ", bonus=" + bonus +
                ", deductions=" + deductions +
                ", grossSalary=" + grossSalary +
                ", netSalary=" + netSalary +
                '}';
    }
}