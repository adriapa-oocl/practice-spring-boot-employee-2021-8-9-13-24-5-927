package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(2,"Cedie",21,"male",500));
        employees.add(new Employee(3,"Kitz",22,"male",1000));
        employees.add(new Employee(4,"Robert",23,"male",500));
        employees.add(new Employee(5,"Kyle",24,"male",1000));
        employees.add(new Employee(6,"Angelo",25,"male",500));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
