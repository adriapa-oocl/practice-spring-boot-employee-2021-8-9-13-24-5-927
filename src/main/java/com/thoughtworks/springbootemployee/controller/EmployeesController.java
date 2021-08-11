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

    public EmployeesController(EmployeeService employeeService) {

    }

//    public EmployeesController() {
//        employees.add(new Employee(1,"Lara",21,"female",1000));
//        employees.add(new Employee(2,"Cedie",21,"male",500));
//        employees.add(new Employee(3,"Kitz",22,"male",1000));
//        employees.add(new Employee(4,"Robert",23,"male",500));
//        employees.add(new Employee(5,"Kyle",24,"male",1000));
//        employees.add(new Employee(6,"Angelo",25,"male",500));
//    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
//        return employees;
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
        Employee newEmployee = new Employee(employees.size()+1,employee.getName(),employee.getAge(),employee.getGender(),employee.getSalary());
        employees.add(newEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeInfo){
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeInfo))
                .get()
                ;
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeInfo) {
        if (employeeInfo.getName() != null){
            employee.setName(employeeInfo.getName());
        }
        if (employeeInfo.getAge() != null){
            employee.setAge(employeeInfo.getAge());
        }
        if (employeeInfo.getGender() != null){
            employee.setGender(employeeInfo.getGender());
        }
        if (employeeInfo.getSalary() != null){
            employee.setSalary(employeeInfo.getSalary());
        }
        return employee;
    }


    @DeleteMapping(path = "/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId){
        employees.remove(findById(employeeId));
        return "Employee Id " + employeeId + " has been successfully deleted.";
    }

}
