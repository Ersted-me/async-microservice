package ru.ersted.asyncmicroservice.service;

import ru.ersted.asyncmicroservice.model.iex.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);
    void delete(Company company);
    Company update(Company company);
    Company getById(Long companyId);
    List<Company> getAll();
}
