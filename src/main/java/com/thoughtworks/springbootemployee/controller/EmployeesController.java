package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(2,"Cedie",21,"male",500));
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId){
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null)
                ;
    }

    @GetMapping(params = {"gender"})
    public Employee findByGender(@RequestParam("gender") String employeeGender){
        return employees
                .stream()
                .filter(employee -> employee.getGender().equals(employeeGender))
                .findFirst()
                .orElse(null)
                ;
    }

}
