package peaksoft.dao.daoimpl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.CompanyDao;
import peaksoft.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = entityManager.createQuery("select c from  Company c ", Company.class).getResultList();
        Comparator<Company> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        companies.sort(comparator);
        return companies;
    }

    @Override
    public void addCompany(Company company) {
        entityManager.persist(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company;
        company = entityManager.find(Company.class, id);
        return company;
    }

    @Override
    public void updateCompany(Company company, Long id) {
        Company company1 = getCompanyById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company1);
    }

    @Override
    public void deleteCompany(Company company) {
        entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
    }
}
