package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees(){
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(2,"Cedie",21,"male",500));
        employees.add(new Employee(3,"Kitz",22,"male",1000));
        employees.add(new Employee(4,"Robert",23,"male",500));
        employees.add(new Employee(5,"Kyle",24,"male",1000));
        employees.add(new Employee(6,"Angelo",25,"male",500));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(employees.size(), actualEmployees.size());
        assertIterableEquals(employees, actualEmployees);
    }
}
