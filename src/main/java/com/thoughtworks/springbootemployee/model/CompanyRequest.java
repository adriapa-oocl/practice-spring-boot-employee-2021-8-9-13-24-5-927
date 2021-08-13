package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class CompanyRequest {

    private String companyName;
    private List<Employee> employees;

    public CompanyRequest(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public CompanyRequest(){

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
}
