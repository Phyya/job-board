package com.Feefee.JobBoard.companies.impl;

import com.Feefee.JobBoard.companies.Company;
import com.Feefee.JobBoard.companies.CompanyRepository;
import com.Feefee.JobBoard.companies.CompanyService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
  private CompanyRepository companyRepository;

  public CompanyServiceImpl(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  @Override
  public Company getCompanyById(Long id) {

    return companyRepository.findById(id).orElse(null);
  }

  @Override
  public void createCompany(Company newCompany) {
    companyRepository.save(newCompany);
  }

  @Override
  public boolean updateCompany(Company newCompany, Long id) {
    Optional<Company> companyOptional = companyRepository.findById(id);
    if (companyOptional.isPresent()) {
      Company company = companyOptional.get();
      company.setCompanyName(newCompany.getCompanyName());
      company.setDescription(newCompany.getDescription());

      companyRepository.save(company);
      return true;
    }

    return false;
  }

  public boolean deleteCompany(Long id) {
    try {
      companyRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      System.out.println(e + "the exception");
      return false;
    }
  }
}
