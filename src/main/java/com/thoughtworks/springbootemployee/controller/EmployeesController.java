package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
//import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

//    @GetMapping(path = "/{employeeId}")
//    public Employee findById(@PathVariable Integer employeeId){
//        return employeeService.findById(employeeId);
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findById(@PathVariable Integer employeeId){
        return employeeMapper.toResponse(employeeService.findById(employeeId));
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender(@RequestParam("gender") String employeeGender){
        return employeeService.findByGender(employeeGender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Employee addEmployee(@RequestBody Employee employeeInfo){
//        return employeeService.addEmployee(employeeInfo);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody EmployeeRequest employeeInfo){
        return employeeService.addEmployee(employeeMapper.toEntity(employeeInfo));
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public List<Employee> getEmployeesByAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return employeeService.getEmployeesByAgeRange(minAge, maxAge);
    }

//    @PutMapping(path = "/{employeeId}")
//    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeInfo){
//        return employeeService.updateEmployee(employeeId, employeeInfo);
//    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeInfo){
        return employeeService.updateEmployee(employeeId, employeeMapper.toEntity(employeeInfo));
    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployee(@PathVariable Integer employeeId){
        return employeeService.removeEmployee(employeeId);
    }

}
