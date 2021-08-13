package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    private List<Employee> testEmployees;

    @BeforeEach
    public void setup(){
        testEmployees = Arrays.asList
                        (new Employee(2,"Kitz",22,"male",10000),
                        (new Employee(3,"Lara",21,"female",10000)),
                        (new Employee(34,"Robert",23,"male",10000)),
                        (new Employee(65,"Angelo",21,"male",10000)),
                        (new Employee(66,"Kyle",23,"male",10000)),
                        (new Employee(67,"Jesse",25,"male",10000)),
                        (new Employee(97,"David",25,"male",10000))
                );
    }

    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Kitz"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000));
    }

    @Test
    void should_create_when_addEmployee_given_employee_information() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\": 98,\n" +
                "    \"name\": \"Joanna\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"salary\": 1000\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Joanna"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("1000"));

    }

    @Test
    void should_update_when_updateEmployee_given_employee_information() throws Exception {
        //given
        final Employee employee = new Employee(95, "Joanna", 25, "female", 1000);
        final Employee savedEmployee = employeeRepository.save(employee);
        String newEmployeeInfo = "{\n" +
                "    \"age\": 22\n" +
                "}";

        //when
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value("22"));
    }

    @Test
    void should_remove_when_removeEmployee_given_employee_id() throws Exception {
        //given
        final Employee employee = new Employee(95, "Joanna", 25, "female", 1000);
        final Employee savedEmployee = employeeRepository.save(employee);

        //when
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_employee_when_findById_given_employee_id() throws Exception {
        int id = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Kitz"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(10000));
    }

    @Test
    void should_return_employees_when_findByGender_given_employee_gender() throws Exception {
        String gender = "male";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", gender)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)));
    }

    @Test
    void should_return_three_employee_per_list_when_getListByPagination_given_pageIndex_is_1_and_pageSize_is_3() throws Exception {
        int pageSize = 3;
        int pageIndex = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("pageIndex", String.valueOf(pageIndex)).param("pageSize", String.valueOf(pageSize))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

}
