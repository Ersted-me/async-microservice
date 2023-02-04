package ru.ersted.asyncmicroservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.model.iex.Company;
import ru.ersted.asyncmicroservice.repository.CompanyRepository;
import ru.ersted.asyncmicroservice.service.CompanyService;

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
        final Company current = getById(company.getId());

        current.setCompanyName(company.getCompanyName());
        current.setVolume(company.getVolume());
        current.setLatestPrice(company.getLatestPrice());
        current.setPreviousVolume(company.getPreviousVolume());

        return companyRepository.save(current);
    }

    @Override
    public Company getById(Long companyId) {
        Optional<Company> optionalCompany = companyRepository.getCompanyById(companyId);

        return optionalCompany.orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Company with id=%d not found", companyId)));
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
