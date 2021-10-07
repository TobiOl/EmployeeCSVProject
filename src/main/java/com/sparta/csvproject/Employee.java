package com.sparta.csvproject;

import java.sql.Date;
import java.util.Objects;

public class Employee {
    private int employeeID;
    private String employeeNamePrefix;
    private String employeeFirstname;
    private char employeeMiddleInitial;
    private String employeeLastName;
    private char employeeGender;
    private String employeeString;
    private Date employeeDoB;
    private Date employeeDoJ;
    private int employeeSalary;

    public Employee(int employeeID, String employeeNamePrefix, String employeeFirstname, char employeeMiddleInitial, String employeeLastName,
                    char employeeGender, String employeeEmail, Date employeeDoB, Date employeeDoJ, int employeeSalary) {
        this.employeeID = employeeID;
        this.employeeNamePrefix = employeeNamePrefix;
        this.employeeFirstname = employeeFirstname;
        this.employeeMiddleInitial = employeeMiddleInitial;
        this.employeeLastName = employeeLastName;
        this.employeeGender = employeeGender;
        this.employeeString = employeeEmail;
        this.employeeDoB = employeeDoB;
        this.employeeDoJ = employeeDoJ;
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeNamePrefix() {
        return employeeNamePrefix;
    }

    public void setEmployeeNamePrefix(String employeeNamePrefix) {
        this.employeeNamePrefix = employeeNamePrefix;
    }

    public String getEmployeeFirstname() {
        return employeeFirstname;
    }

    public void setEmployeeFirstname(String employeeFirstname) {
        this.employeeFirstname = employeeFirstname;
    }

    public char getEmployeeMiddleInitial() {
        return employeeMiddleInitial;
    }

    public void setEmployeeMiddleInitial(char employeeMiddleInitial) {
        this.employeeMiddleInitial = employeeMiddleInitial;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public char getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(char employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeString() {
        return employeeString;
    }

    public void setEmployeeString(String employeeString) {
        this.employeeString = employeeString;
    }

    public Date getEmployeeDoB() {
        return employeeDoB;
    }

    public void setEmployeeDoB(Date employeeDoB) {
        this.employeeDoB = employeeDoB;
    }

    public Date getEmployeeDoJ() {
        return employeeDoJ;
    }

    public void setEmployeeDoJ(Date employeeDoJ) {
        this.employeeDoJ = employeeDoJ;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public boolean CheckIfValid(){
        boolean result = true;
        if (Objects.isNull(this.getEmployeeID())||Objects.isNull(this.getEmployeeDoJ())||Objects.isNull(this.getEmployeeDoB())||Objects.isNull(this.getEmployeeGender())
                ||Objects.isNull(this.getEmployeeFirstname())
                ||Objects.isNull(this.getEmployeeLastName())||Objects.isNull(this.getEmployeeNamePrefix())||Objects.isNull(this.getEmployeeMiddleInitial())||Objects.isNull(this.getEmployeeSalary())
                ||Objects.isNull(this.getEmployeeString())){
            result = false;
        }
        return result;
    }
    @Override
    public boolean equals(Object other) {
        if (this == null){
            return false;
        }
        if (this==other){
            return true;
        }
        Employee employee = (Employee) other;
        return employeeID ==employee.employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }

}
