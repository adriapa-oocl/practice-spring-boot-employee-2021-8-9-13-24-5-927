package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);

        return employeeResponse;
    }

    public List<EmployeeResponse> toResponseList(List<Employee> employeeList){
        return employeeList
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}