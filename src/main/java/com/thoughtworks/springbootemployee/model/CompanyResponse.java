package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

public class CompanyResponse {
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

    public CompanyResponse(String companyName, List<Employee> employees) {
        this.companyName = companyName;
    }

    public CompanyResponse(){

    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
