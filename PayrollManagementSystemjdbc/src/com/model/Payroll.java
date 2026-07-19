package com.model;

public class Payroll {

    private int payrollId;
    private int employeeId;
    private String month;
    private int year;
    private String paymentDate;
    private String salaryStatus;

    // Default Constructor
    public Payroll() {
    }

    // Parameterized Constructor
    public Payroll(int payrollId, int employeeId, String month,
                   int year, String paymentDate, String salaryStatus) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.paymentDate = paymentDate;
        this.salaryStatus = salaryStatus;
    }

    // Getters and Setters

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollId=" + payrollId +
                ", employeeId=" + employeeId +
                ", month='" + month + '\'' +
                ", year=" + year +
                ", paymentDate='" + paymentDate + '\'' +
                ", salaryStatus='" + salaryStatus + '\'' +
                '}';
    }
}