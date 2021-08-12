package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
//import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.service.RetiringEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private RetiringEmployeeService retiringEmployeeService;
    private List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAllEmployees(){
        return retiringEmployeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId){
        return retiringEmployeeService.findById(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender(@RequestParam("gender") String employeeGender){
        return retiringEmployeeService.findByGender(employeeGender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return retiringEmployeeService.getEmployeesByPagination(pageIndex, pageSize);
    }

//    @GetMapping(params = {"minAge", "maxAge"})
//    public List<Employee> getEmployeesByAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
//        return retiringEmployeeService.getEmployeesByAgeRange(minAge, maxAge);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employeeInfo){
        return retiringEmployeeService.addEmployee(employeeInfo);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeInfo){
        return retiringEmployeeService.updateEmployee(employeeId, employeeInfo);
    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployee(@PathVariable Integer employeeId){
        return retiringEmployeeService.removeEmployee(employeeId);
    }

}
