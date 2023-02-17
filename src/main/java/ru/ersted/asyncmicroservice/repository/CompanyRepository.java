package ru.ersted.asyncmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.entity.SymbolEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<StockEntity, Long> {
//    Optional<SymbolEntity> getCompanyById(Long id);
//
//    Optional<SymbolEntity> getCompanyByCompanyName(String companyName);
//
//    @Query(value = "SELECT * FROM company where latest_price notnull order by latest_price DESC, volume DESC, previous_volume DESC, company_name LIMIT :count",
//            nativeQuery = true)
//    List<SymbolEntity> getMostExpensiveCompanies(@Param("count") Long count);
//
//    @Query(value = "SELECT * FROM company where percent_cost notnull order by percent_cost DESC, company_name LIMIT :count",
//            nativeQuery = true)
//    List<SymbolEntity> getCompaniesGreatestChangePercentStockValue(@Param("count") Long count);

}
