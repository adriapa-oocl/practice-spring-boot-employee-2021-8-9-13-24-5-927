package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;
    private List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
//        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId){
        return employeeService.findById(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender(@RequestParam("gender") String employeeGender){
        return employeeService.findByGender(employeeGender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employees
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        Employee newEmployee = new Employee(employees.size()+1,employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeGender(),employee.getEmployeeSalary());
        employees.add(newEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeInfo){
        return employees
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeInfo))
                .get()
                ;
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeInfo) {
        if (employeeInfo.getEmployeeName() != null){
            employee.setEmployeeName(employeeInfo.getEmployeeName());
        }
        if (employeeInfo.getEmployeeAge() != null){
            employee.setEmployeeAge(employeeInfo.getEmployeeAge());
        }
        if (employeeInfo.getEmployeeGender() != null){
            employee.setEmployeeGender(employeeInfo.getEmployeeGender());
        }
        if (employeeInfo.getEmployeeSalary() != null){
            employee.setEmployeeSalary(employeeInfo.getEmployeeSalary());
        }
        return employee;
    }

    @DeleteMapping(path = "/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId){
        employees.remove(findById(employeeId));
        return "Employee Id " + employeeId + " has been successfully deleted.";
    }

}
