package ru.ersted.asyncmicroservice.service.db.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.model.iex.Company;
import ru.ersted.asyncmicroservice.repository.CompanyRepository;
import ru.ersted.asyncmicroservice.service.db.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public Company update(Company company) {
        Company current = getById(company.getId());

        company.setId(current.getId());
        company.setPercentCost(calculatePercentCost(current, company));
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public Company saveOrUpdate(Company company) {
        Optional<Company> current = companyRepository.getCompanyByCompanyName(company.getCompanyName());

        if(current.isEmpty())
            return save(company);

        company.setId(current.get().getId());
        if(company.getLatestPrice() != null)
            company.setPercentCost(calculatePercentCost(current.get(), company));

        return save(company);
    }
    private double calculatePercentCost(Company old, Company now){
        double currentPrice = old.getLatestPrice();
        double newPrice = now.getLatestPrice();
        return (newPrice - currentPrice)/currentPrice * 100;
    }

    public List<Company> getMostExpensiveCompanies(Long count){
        return companyRepository.getMostExpensiveCompanies(count);
    }

    public List<Company> getCompaniesGreatestChangePercentStockValue(Long count){
        return companyRepository.getCompaniesGreatestChangePercentStockValue(count);
    }

    @Override
    public Company getById(Long companyId) {
        Optional<Company> optionalCompany = companyRepository.getCompanyById(companyId);

        return optionalCompany.orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Company with id=%d not found.", companyId)));
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
