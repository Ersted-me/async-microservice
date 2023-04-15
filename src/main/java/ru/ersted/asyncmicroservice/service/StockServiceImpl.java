package ru.ersted.asyncmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.repository.StockRepository;
import ru.ersted.asyncmicroservice.utils.ConsoleUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    @Override
    public void show5HighestValueStocks() {
        List<StockEntity> highestValueStocks = stockRepository.get5HighestValueStocks();
        List<StockEntity> greatestChangePercentStockValue = stockRepository.get5GreatestChangePercentStockValue();

        ConsoleUtils.viewAllCompaniesData(highestValueStocks, "The top 5 highest value stocks.");
        ConsoleUtils.viewAllCompaniesData(greatestChangePercentStockValue,
                "The most recent 5 companies that have the greatest change percent in stock value.");
    }
}
