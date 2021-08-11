package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employeesList;

    public Company(String companyName, Integer employeesNumber, List<Employee> employeesList) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employeesList = employeesList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }
}
