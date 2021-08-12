package com.thoughtworks.springbootemployee.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Integer companyId;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> companyEmployees = new ArrayList<>();

    public Company(Integer companyId, String companyName, Integer employeesNumber, List<Employee> companyEmployees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.companyEmployees = companyEmployees;
    }

    public Company(){

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employee> getEmployees() {
        return companyEmployees;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setCompanyEmployees(List<Employee> companyEmployees) {
        this.companyEmployees = companyEmployees;
    }
}
