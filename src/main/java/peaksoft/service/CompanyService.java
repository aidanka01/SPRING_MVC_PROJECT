package peaksoft.service;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void addCompany(Company company);

    Company getCompanyById(long id);

    void updateCompany(Company company, Long id);

    void deleteCompany(Company company);
}