package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
       this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee findById(Integer employeeId){
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null)
                ;
    }

    public List<Employee> findByGender(String employeeGender){
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getEmployeeGender().equals(employeeGender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return getAllEmployees()
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employeeInfo) {
        Employee newEmployee = new Employee(employeeRepository.getEmployees().size()+1,
                employeeInfo.getEmployeeName(), employeeInfo.getEmployeeAge(), employeeInfo.getEmployeeGender(), employeeInfo.getEmployeeSalary());
        employeeRepository.getEmployees().add(newEmployee);
        return newEmployee;
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeInfo){
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeInfo))
                .get()
                ;
    }

    public Employee removeEmployee(Integer employeeId){
        Employee removeEmployee = employeeRepository.getEmployees().stream()
                                        .filter(employee -> employee.getEmployeeId()
                                        .equals(employeeId))
                                        .findFirst().orElse(null);
        employeeRepository.getEmployees().remove(removeEmployee);
        return removeEmployee;
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

}
