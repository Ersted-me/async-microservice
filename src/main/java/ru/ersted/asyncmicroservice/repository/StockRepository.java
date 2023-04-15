package ru.ersted.asyncmicroservice.repository;

import ru.ersted.asyncmicroservice.entity.StockEntity;

import java.util.List;

public interface StockRepository {
    void saveAll(List<StockEntity> entities);

    List<StockEntity> get5HighestValueStocks();
    List<StockEntity> get5GreatestChangePercentStockValue();
}
