package ru.ersted.asyncmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ersted.asyncmicroservice.model.iex.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> getCompanyById(Long id);
}
