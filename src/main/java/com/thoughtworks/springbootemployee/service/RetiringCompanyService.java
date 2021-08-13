package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiringCompanyService {
    @Resource
    private RetiringCompanyRepository retiringCompanyRepository;

    public RetiringCompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies(){
        return retiringCompanyRepository.getCompanies();
    }

    public Company findById(Integer companyId) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
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
        Company newCompany = new Company(retiringCompanyRepository.getCompanies().size()+1,
                companyInfo.getCompanyName(), companyInfo.getEmployees());
        retiringCompanyRepository.getCompanies().add(newCompany);
        return newCompany;
    }

    public Company updateCompany(Integer companyId, Company companyInfo) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .map(company -> updateCompanyInfo(company, companyInfo))
                .get();
    }

    public Company removeCompany(Integer companyId) {
        Company removeCompany = retiringCompanyRepository.getCompanies().stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst().orElse(null);
        retiringCompanyRepository.getCompanies().remove(removeCompany);
        return removeCompany;
    }

    private Company updateCompanyInfo(Company company, Company companyInfo) {
        if (companyInfo.getCompanyName() != null){
            company.setCompanyName(companyInfo.getCompanyName());
        }
        if (!companyInfo.getEmployees().isEmpty() && companyInfo.getEmployees() != null){
            company.setEmployees(companyInfo.getEmployees());
        }
        return company;
    }

}
