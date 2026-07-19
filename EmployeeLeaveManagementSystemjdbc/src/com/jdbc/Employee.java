package com.jdbc;

import java.time.LocalDate;

public class Employee {

    // Employee Table
    private int employeeId;
    private String employeeName;
    private String department;
    private String email;
    private String phone;

    // Leave Request Table
    private int leaveId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int leaveDays;
    private String reason;
    private String status;

    // Leave Balance Table
    private int totalLeaves;
    private int usedLeaves;
    private int remainingLeaves;

    // Default Constructor
    public Employee() {

    }

    // Employee Constructor
    public Employee(int employeeId, String employeeName, String department,
                    String email, String phone) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    // Full Constructor
    public Employee(int employeeId, String employeeName, String department,
                    String email, String phone,
                    int leaveId, LocalDate fromDate, LocalDate toDate,
                    int leaveDays, String reason, String status,
                    int totalLeaves, int usedLeaves, int remainingLeaves) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.email = email;
        this.phone = phone;

        this.leaveId = leaveId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveDays = leaveDays;
        this.reason = reason;
        this.status = status;

        this.totalLeaves = totalLeaves;
        this.usedLeaves = usedLeaves;
        this.remainingLeaves = remainingLeaves;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }

    public void setUsedLeaves(int usedLeaves) {
        this.usedLeaves = usedLeaves;
    }

    public int getRemainingLeaves() {
        return remainingLeaves;
    }

    public void setRemainingLeaves(int remainingLeaves) {
        this.remainingLeaves = remainingLeaves;
    }

    @Override
    public String toString() {

        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", leaveId=" + leaveId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", leaveDays=" + leaveDays +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", totalLeaves=" + totalLeaves +
                ", usedLeaves=" + usedLeaves +
                ", remainingLeaves=" + remainingLeaves +
                '}';
    }

}