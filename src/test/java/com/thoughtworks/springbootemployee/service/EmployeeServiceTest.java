package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private RetiringEmployeeService retiringEmployeeService;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private RetiringEmployeeRepository retiringEmployeeRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private List<Employee> testEmployees;

    @BeforeEach
    public void setup(){
        testEmployees = Arrays.asList
                (new Employee(1, "Lara", 21, "female", 1000),
                        (new Employee(2,"Cedie",21,"male",500)),
                        (new Employee(3,"Kitz",22,"male",1000)),
                        (new Employee(4,"Robert",23,"male",500)),
                        (new Employee(5,"Kyle",24,"male",1000))
                );
    }

    @Test
    void should_return_all_employees_when_getAllEmployees_given_all_employees(){
        //given
        given(employeeRepository.findAll()).willReturn(testEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(testEmployees.size(), actualEmployees.size());
        assertIterableEquals(testEmployees, actualEmployees);
    }

    @Test
    void should_return_specific_employee_when_findById_given_employee_id() {
        //given
        given(retiringEmployeeRepository.getEmployees()).willReturn(testEmployees);

        //when
        Employee mockEmployee = new Employee(2, "Cedie", 23, "Male", 5000);
        Employee actualEmployee = retiringEmployeeService.findById(2);

        //then
        assertEquals(mockEmployee.getId(), actualEmployee.getId());
    }

    @Test
    void should_return_specific_employee_when_findByGender_given_employee_gender() {
        //given
        given(retiringEmployeeRepository.getEmployees()).willReturn(testEmployees);

        //when
        List<Employee> actualEmployees = retiringEmployeeService.findByGender("male");

        //then
        assertEquals(4,actualEmployees.stream().map(Employee::getGender).filter(employeeGender -> employeeGender.equals("male")).count());
        assertEquals(4,actualEmployees.size());
    }

    @Test
    void should_return_three_employee_per_list_when_getListByPagination_given_pageIndex_is_1_and_pageSize_is_3() {
        //given
        given(retiringEmployeeRepository.getEmployees()).willReturn(testEmployees);
        int mockCount = 3;

        //when
        int actualCount = retiringEmployeeService.getEmployeesByPagination(1, 3).size();

        //then
        assertEquals(mockCount, actualCount);
    }

    @Test
    void should_return_new_employee_when_addEmployee_given_employee_info() {
        //given
        List<Employee> employees = new ArrayList<>();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        Employee newEmployee = new Employee(){{
            setName("David");
            setAge(25);
            setGender("male");
            setSalary(1000);
        }
        };

        //when
        retiringEmployeeService.addEmployee(newEmployee);

        //then
        assertEquals(1, employees.size());
        assertEquals(25, employees.get(0).getAge());
    }

    @Test
    void should_update_existing_employee_when_updateEmployee_given_employee_info() {
        //given
        given(retiringEmployeeRepository.getEmployees()).willReturn(testEmployees);
        Employee updateEmployee = new Employee(){{
            setAge(22);
        }};

        //when
        Employee updatedEmployeeInfo = retiringEmployeeService.updateEmployee(1, updateEmployee);

        //then
        assertEquals(updatedEmployeeInfo.getAge(), updateEmployee.getAge());
    }

    @Test
    void should_remove_existing_employee_when_removeEmployee_given_employee_id() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Lara",21,"female",1000));
        employees.add(new Employee(2,"Cedie",21,"male",500));
        employees.add(new Employee(3,"Kitz",22,"male",1000));
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);

        //when
        Employee deletedEmployee = retiringEmployeeService.removeEmployee(1);

        //then
        assertNotNull(deletedEmployee);
        assertEquals(2, employees.size());
    }
}
