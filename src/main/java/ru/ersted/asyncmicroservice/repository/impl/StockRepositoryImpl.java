package ru.ersted.asyncmicroservice.repository.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.repository.StockRepository;

import java.sql.PreparedStatement;
import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class StockRepositoryImpl implements StockRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void saveAll(List<StockEntity> entities) {
        log.info("List size: {}", entities.size());
        String sqlQuery = "insert into STOCK as s" +
                "    (company_name, previous_volume, volume, latest_price)" +
                "values (?, ?, ?, ?)" +
                "on conflict(company_name)" +
                "    DO UPDATE SET\n" +
                "                  profitability= s.latest_price - excluded.latest_price," +
                "                  previous_volume = excluded.previous_volume;";
        jdbcTemplate.batchUpdate(
                sqlQuery,
                entities,
                entities.size(),
                (PreparedStatement ps, StockEntity se) -> {
                    ps.setString(1, se.getCompanyName());
                    ps.setLong(2, se.getPreviousVolume());
                    ps.setLong(3, se.getVolume());
                    ps.setDouble(4, se.getLatestPrice());
                });
    }
}
