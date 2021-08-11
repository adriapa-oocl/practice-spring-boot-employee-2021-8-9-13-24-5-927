package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private List<Employee> employees = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();

    public CompanyController() {
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(2,"Cedie",21,"male",500));
        employees.add(new Employee(3,"Kitz",22,"male",1000));
        employees.add(new Employee(4,"Robert",23,"male",500));
        employees.add(new Employee(5,"Kyle",24,"male",1000));
        employees.add(new Employee(6,"Angelo",25,"male",500));
        companies.add(new Company("OOCL", 200, employees));
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companies;
    }
}
