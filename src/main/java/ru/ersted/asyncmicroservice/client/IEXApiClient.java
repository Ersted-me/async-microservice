package ru.ersted.asyncmicroservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ersted.asyncmicroservice.dto.StockDto;
import ru.ersted.asyncmicroservice.dto.SymbolDto;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class IEXApiClient {

    private final RestTemplate restTemplate;

    @Value("${custom.iex.url}")
    private String IEX_URL;

    @Value("${custom.iex.token}")
    private String TOKEN;

    private final String PATH_TO_SYMBOLS = "stable/ref-data/symbols";
    private final String PATH_TO_STOCK_DATA = "stable/stock/%s/quote";

    //TODO: investigate WebClient + HttpClient
    @Async
    public CompletableFuture<ResponseEntity<SymbolDto[]>> getSymbolsList() {
        return CompletableFuture.supplyAsync(
                () -> restTemplate.getForEntity(IEX_URL + PATH_TO_SYMBOLS + "?token=" + TOKEN, SymbolDto[].class));
    }

    @Async
    public CompletableFuture<ResponseEntity<StockDto>> getCompanyDataBySymbol(String symbol) {
        //TODO: add async and parallel calculation
        //log.info("Getting company by {}", symbol);
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForEntity(
                                String.format(IEX_URL + PATH_TO_STOCK_DATA + "?token=" + TOKEN, symbol), StockDto.class))
                .handle((res, ex) -> {
                    if (ex != null) {
                        log.warn("Symbol: {} has problem {}", symbol, ex.getCause().getMessage());
                        return null;
                    }
                    return res;
                });
    }


}
