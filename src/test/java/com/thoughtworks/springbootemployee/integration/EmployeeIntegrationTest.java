package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    public void setup(){
        employeeService.addEmployee(new Employee(1,"Kitz",22,"male",10000));
        employeeService.addEmployee(new Employee(2,"Lara",21,"female",10000));
        employeeService.addEmployee(new Employee(3,"Robert",23,"male",10000));
        employeeService.addEmployee(new Employee(4,"Angelo",21,"male",10000));
        employeeService.addEmployee(new Employee(5,"Kyle",23,"male",10000));
        employeeService.addEmployee(new Employee(6,"Jesse",24,"male",10000));
        employeeService.addEmployee(new Employee(7,"David",24,"male",10000));
        employeeService.addEmployee(new Employee(8,"Cedie",25,"male",10000));
    }

    @AfterEach
    public void  deleteDataAfter() {
        employeeService.deleteAll();
    }

    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Kitz"))
                .andExpect(jsonPath("$[7].name").value("Cedie"));
    }

}
