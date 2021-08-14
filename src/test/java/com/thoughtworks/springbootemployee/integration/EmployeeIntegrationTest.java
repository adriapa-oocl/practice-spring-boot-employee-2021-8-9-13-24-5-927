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
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Kitz"))
                .andExpect(jsonPath("$[7].name").value("Cedie"));
    }

    @Test
    void should_create_when_addEmployee_given_employee_information() throws Exception {
        String employee = "{\n" +
                "    \"id\": 9,\n" +
                "    \"name\": \"Joanna\",\n" +
                "    \"age\": 20,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"salary\": 10000\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Joanna"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("10000"));

    }

    @Test
    void should_update_when_updateEmployee_given_employee_information() throws Exception {
        final Employee employee = new Employee(95, "Will", 30, "30", 1000);
        final Employee savedEmployee = employeeService.addEmployee(employee);
        String newEmployeeInfo = "{\n" +
                "    \"age\": 27\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Will"))
                .andExpect(jsonPath("$.age").value("27"));
    }

    @Test
    void should_remove_when_removeEmployee_given_employee_id() throws Exception {
        final Employee employee = new Employee(95, "Joanna", 25, "female", 1000);
        final Employee savedEmployee = employeeService.addEmployee(employee);

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_employee_when_findById_given_employee_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lara"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(10000));
    }

    @Test
    void should_return_employees_when_findByGender_given_employee_gender() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "female")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "male")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)));
    }

}
