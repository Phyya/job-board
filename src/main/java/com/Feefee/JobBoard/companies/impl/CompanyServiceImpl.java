package com.Feefee.JobBoard.companies.impl;

import com.Feefee.JobBoard.companies.Company;
import com.Feefee.JobBoard.companies.CompanyRepository;
import com.Feefee.JobBoard.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {
private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies()
    {
return companyRepository.findAll();
    }

}
