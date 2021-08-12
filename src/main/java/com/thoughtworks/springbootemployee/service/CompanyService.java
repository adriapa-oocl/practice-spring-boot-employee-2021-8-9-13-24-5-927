package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Resource
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.getCompanies();
    }

    public Company findById(Integer companyId) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getAllEmployeesByCompanyId(Integer companyId) {
        return findById(companyId).getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return getAllCompanies()
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company addCompany(Company companyInfo) {
        Company newCompany = new Company(companyRepository.getCompanies().size()+1,
                companyInfo.getCompanyName(), companyInfo.getEmployeesNumber(), companyInfo.getEmployees());
        companyRepository.getCompanies().add(newCompany);
        return newCompany;

    }
}
