package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
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
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null)
                ;
    }

    public List<Employee> findByGender(String employeeGender){
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(employeeGender))
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
                employeeInfo.getName(), employeeInfo.getAge(), employeeInfo.getGender(), employeeInfo.getSalary());
        retiringEmployeeRepository.getEmployees().add(newEmployee);
        return newEmployee;
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeInfo){
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeInfo))
                .get()
                ;
    }

    public Employee removeEmployee(Integer employeeId){
        Employee removeEmployee = retiringEmployeeRepository.getEmployees().stream()
                                        .filter(employee -> employee.getId()
                                        .equals(employeeId))
                                        .findFirst().orElse(null);
        retiringEmployeeRepository.getEmployees().remove(removeEmployee);
        return removeEmployee;
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
}
