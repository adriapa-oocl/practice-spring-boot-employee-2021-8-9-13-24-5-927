package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private List<Company> testCompanies = new ArrayList<>();

    public CompanyServiceTest() {
    }

    @BeforeEach
    public void setup(){
        testCompanies.add(new Company(1,"OOCL", 200, Arrays.asList(
                new Employee(1,"Lara",21,"female",1000),
                new Employee(3,"Kitz",22,"male",1000),
                new Employee(4,"Robert",23,"male",500),
                new Employee(5,"Kyle",24,"male",1000),
                new Employee(6,"Angelo",25,"male",500))));
        testCompanies.add(new Company(2, "DPMCP", 100, Arrays.asList(
                new Employee(2,"Cedie",27,"male",500),
                new Employee(7,"Jesse",25,"male",500),
                new Employee(8,"David",25,"male",500))));
    }

    @Test
    void should_return_all_companies_when_getAllCompanies_given_all_companies() {
        //given
        given(companyRepository.getCompanies()).willReturn(testCompanies);

        //when
        List<Company> actualCompanies = companyService.getAllCompanies();

        //then
        assertEquals(testCompanies.size(), actualCompanies.size());
        assertIterableEquals(testCompanies, actualCompanies);
    }

    @Test
    void should_return_specific_company_when_findById_given_company_id() {
        //given
        given(companyRepository.getCompanies()).willReturn(testCompanies);

        //when
        Company actualCompany = companyService.findById(2);

        //then
        assertEquals(testCompanies.get(1).getCompanyId(), actualCompany.getCompanyId());
    }

    @Test
    void should_return_all_employees_when_getAllEmployeesByCompanyId_given_company_id() {
        //given
        given(companyRepository.getCompanies()).willReturn(testCompanies);

        //when
        Company actualCompany = companyService.getAllEmployeesByCompanyId(2);

        //then
        assertEquals(testCompanies.get(0).getEmployeesList(), actualCompany.getEmployeesList());
    }
}
