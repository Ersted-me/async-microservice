package ru.ersted.asyncmicroservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ersted.asyncmicroservice.dto.StockDto;
import ru.ersted.asyncmicroservice.dto.SymbolDto;

import java.util.concurrent.CompletableFuture;

@Component
public class IEXApiClient {

    private final RestTemplate restTemplate;
    @Value("${custom.iex.url}")
    private String IEX_URL;

    private final String PATH_TO_SYMBOLS =
            "stable/ref-data/symbols";
    private final String PATH_TO_STOCK_DATA =
            "stable/stock/%s/quote";
    @Value("${custom.iex.token}")
    private String TOKEN;

    @Autowired
    public IEXApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //TODO: investigate WebClient + HttpClient
    public CompletableFuture<SymbolDto[]> getSymbolsList() {
        return CompletableFuture.supplyAsync(
                () -> restTemplate.getForEntity(IEX_URL + PATH_TO_SYMBOLS + "?token=" + TOKEN, SymbolDto[].class).getBody());
    }

    public CompletableFuture<ResponseEntity<StockDto>> getCompanyDataBySymbol(String symbol) {

        //TODO: add async and parallel calculation

        return CompletableFuture.supplyAsync(
                () -> restTemplate.getForEntity(String.format(IEX_URL + PATH_TO_STOCK_DATA + "?token=" + TOKEN, symbol), StockDto.class));
    }


}
