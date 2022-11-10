package peaksoft.dao;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies();

    void addCompany(Company company);

    Company getCompanyById(Long id);

    void updateCompany(Company company, Long id);

    void deleteCompany(Company company);
}
