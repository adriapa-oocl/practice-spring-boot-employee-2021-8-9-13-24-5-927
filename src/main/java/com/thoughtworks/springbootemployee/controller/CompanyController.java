package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.RetiringCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private RetiringCompanyService retiringCompanyService;

    @Autowired
    private CompanyService companyService;

    private List<Company> companies = new ArrayList<>();

    public CompanyController() {

    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company findById(@PathVariable Integer companyId){
        return companyService.findById(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable Integer companyId){
        return companyService.getAllEmployeesByCompanyId(companyId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyService.getCompaniesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company companyInfo){
        return companyService.addCompany(companyInfo);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody Company companyInfo){
        return companyService.updateCompany(companyId, companyInfo);
    }

    @DeleteMapping(path = "/{companyId}")
    public Company deleteCompany(@PathVariable Integer companyId){
        return companyService.removeCompany(companyId);
    }

}
