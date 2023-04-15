package ru.ersted.asyncmicroservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Async
    public CompletableFuture<ResponseEntity<SymbolDto[]>> getSymbolsList() {
        return CompletableFuture.supplyAsync(
                () -> restTemplate.getForEntity(IEX_URL + PATH_TO_SYMBOLS + "?token=" + TOKEN, SymbolDto[].class))
                .handle((res, ex) -> {
                    if (ex != null) {
                        log.warn("getSymbolsList: the companies were not received.");
                        return new ResponseEntity<>(null, null);
                    }
                    log.info("getSymbolsList: the companies were received.");
                    return res;
                });
    }

    @Async
    public CompletableFuture<ResponseEntity<StockDto>> getCompanyDataBySymbol(String symbol) {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForEntity(
                                String.format(IEX_URL + PATH_TO_STOCK_DATA + "?token=" + TOKEN, symbol), StockDto.class))
                .handle((res, ex) -> {
                    if (ex != null) {
                        log.warn("getCompanyDataBySymbol: {} has problem {}", symbol, ex.getCause().getMessage());
                        return new ResponseEntity<>(null, null);
                    }
                    log.info("getCompanyDataBySymbol: {}", symbol);
                    return res;
                });
    }


}
