package ru.ersted.asyncmicroservice.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ersted.asyncmicroservice.service.StockService;

@Component
@RequiredArgsConstructor
public class ShowStocksJob {

    private final StockService stockService;

    @Scheduled(initialDelay = 1000L, fixedDelay = 10000L)
    public void show5HighestValueStocks() {
        stockService.show5HighestValueStocks();
    }
}
