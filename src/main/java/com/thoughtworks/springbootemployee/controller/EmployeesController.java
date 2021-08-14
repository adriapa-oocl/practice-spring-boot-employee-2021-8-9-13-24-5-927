package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
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
    public List<EmployeeResponse> getAllEmployees(){
        return employeeMapper.toResponseList(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findById(@PathVariable Integer employeeId){
        return employeeMapper.toResponse(employeeService.findById(employeeId));
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> findByGender(@RequestParam("gender") String employeeGender){
        return employeeMapper.toResponseList(employeeService.findByGender(employeeGender));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest){
        final Employee employee = employeeService.addEmployee(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public List<Employee> getEmployeesByAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return employeeService.getEmployeesByAgeRange(minAge, maxAge);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        Employee updateEmployee = employeeService.updateEmployee(employeeId, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(updateEmployee);
    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployee(@PathVariable Integer employeeId){
        return employeeService.removeEmployee(employeeId);
    }

}
