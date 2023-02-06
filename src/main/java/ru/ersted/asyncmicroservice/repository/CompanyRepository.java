package ru.ersted.asyncmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ersted.asyncmicroservice.model.iex.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> getCompanyById(Long id);

    Optional<Company> getCompanyByCompanyName(String companyName);

    @Query(value = "SELECT * FROM company where latest_price notnull order by latest_price DESC, volume DESC, previous_volume DESC, company_name LIMIT :count",
            nativeQuery = true)
    List<Company> getMostExpensiveCompanies(@Param("count") Long count);

    @Query(value = "SELECT * FROM company where percent_cost notnull order by percent_cost DESC, company_name LIMIT :count",
            nativeQuery = true)
    List<Company> getCompaniesGreatestChangePercentStockValue(@Param("count") Long count);

}
