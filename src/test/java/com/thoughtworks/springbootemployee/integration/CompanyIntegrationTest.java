package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyService companyService;

    @BeforeEach
    public void setup(){
        List<Employee> employeeListOOCL = new ArrayList<>();
        employeeListOOCL.add(new Employee(1,"Kitz",22,"male",10000));
        employeeListOOCL.add(new Employee(2,"Lara",21,"female",10000));
        employeeListOOCL.add(new Employee(4,"Angelo",21,"male",10000));
        employeeListOOCL.add(new Employee(5,"Kyle",23,"male",10000));
        List<Employee> employeeListDPMCP = new ArrayList<>();
        employeeListDPMCP.add(new Employee(3,"Robert",23,"male",10000));
        employeeListDPMCP.add(new Employee(6,"Jesse",24,"male",10000));
        employeeListDPMCP.add(new Employee(7,"David",24,"male",10000));
        employeeListDPMCP.add(new Employee(8,"Cedie",25,"male",10000));
        Company companyOOCL = new Company(1, "OOCL", employeeListOOCL);
        companyService.addCompany(companyOOCL);
        Company companyDPMCP = new Company(2, "DPMCP", employeeListDPMCP);
        companyService.addCompany(companyDPMCP);
    }

    @AfterEach
    public void  deleteDataAfter() {
        companyService.deleteAll();
    }

    @Test
    void should_return_all_companies_when_getAllCompanies() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}
