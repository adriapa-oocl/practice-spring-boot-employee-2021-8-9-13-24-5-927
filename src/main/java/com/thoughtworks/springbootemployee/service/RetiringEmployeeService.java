package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiringEmployeeService {
    @Resource
    private RetiringEmployeeRepository retiringEmployeeRepository;

    public RetiringEmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
       this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return retiringEmployeeRepository.getEmployees();
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
//        return retiringEmployeeRepository.save(employeeInfo);
        Employee newEmployee = new Employee(retiringEmployeeRepository.getEmployees().size()+1,
                employeeInfo.getEmployeeName(), employeeInfo.getEmployeeAge(), employeeInfo.getEmployeeGender(), employeeInfo.getEmployeeSalary());
        retiringEmployeeRepository.getEmployees().add(newEmployee);
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
        Employee removeEmployee = retiringEmployeeRepository.getEmployees().stream()
                                        .filter(employee -> employee.getEmployeeId()
                                        .equals(employeeId))
                                        .findFirst().orElse(null);
        retiringEmployeeRepository.getEmployees().remove(removeEmployee);
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
