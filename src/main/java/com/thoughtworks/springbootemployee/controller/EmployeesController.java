package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employees;
    }

    public EmployeesController() {
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(1,"Cedie",21,"male",500));
    }
}
