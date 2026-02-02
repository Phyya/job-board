package com.Feefee.JobBoard.companies;

import java.util.List;

public interface CompanyService {

  List<Company> getAllCompanies();

  Company getCompanyById(Long id);

  boolean updateCompany(Company newCompany, Long id);

  boolean deleteCompany(Long id);

  void createCompany(Company newCompany);
}
