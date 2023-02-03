package ru.ersted.asyncmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ersted.asyncmicroservice.model.iex.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
