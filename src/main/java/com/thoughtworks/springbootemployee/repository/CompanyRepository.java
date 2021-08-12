package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        companies.add(new Company(1,"OOCL", 200, Arrays.asList(
                new Employee(1,"Lara",21,"female",1000),
                new Employee(3,"Kitz",22,"male",1000),
                new Employee(4,"Robert",23,"male",500),
                new Employee(5,"Kyle",24,"male",1000),
                new Employee(6,"Angelo",25,"male",500))));
        companies.add(new Company(2, "DPMCP", 100, Arrays.asList(
                new Employee(2,"Cedie",27,"male",500),
                new Employee(7,"Jesse",25,"male",500),
                new Employee(8,"David",25,"male",500))));
    }

    public List<Company> getCompanies(){
        return companies;
    }

}
