package ru.ersted.asyncmicroservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ersted.asyncmicroservice.entity.StockEntity;

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

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "insert into STOCK as s" +
            "    (company_name, previous_volume, volume, latest_price)" +
            "values (:#{#stockEntity.companyName}, :#{#stockEntity.previousVolume}, :#{#stockEntity.volume}, :#{#stockEntity.latestPrice})" +
            "on conflict(company_name)" +
            "    DO UPDATE SET" +
            "                  profitability= s.latest_price - excluded.latest_price," +
            "                  previous_volume = excluded.previous_volume;")
    void saveOrUpdate(@Param("stockEntity") StockEntity stockEntity);

}
