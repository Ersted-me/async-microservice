package ru.ersted.asyncmicroservice.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ersted.asyncmicroservice.model.iex.Company;
import ru.ersted.asyncmicroservice.model.iex.Symbol;
import ru.ersted.asyncmicroservice.model.util.URL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class IEXApi {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${custom.iex.host}")
    private String IEX_HOST;
    @Value("${custom.iex.protocol}")
    private String IEX_PROTOCOL;
    private final String PATH_TO_SYMBOLS =
            "stable/ref-data/symbols";
    private final String PATH_TO_COMPANY_DATA =
            "stable/stock/%s/quote";
    @Value("${custom.iex.token}")
    private String TOKEN;

    public List<Symbol> getSymbolsList() {
        URL url = new URL(IEX_PROTOCOL, IEX_HOST, PATH_TO_SYMBOLS, "token=" + TOKEN);
        System.out.println(url);
        ResponseEntity<Symbol[]> response = restTemplate.getForEntity(url.toString(), Symbol[].class);
        return response.getBody() != null ? Arrays.asList(response.getBody()) : Collections.emptyList();
    }

    public Company getCompanyDataBySymbol(String symbol) {
        URL url = new URL(IEX_PROTOCOL, IEX_HOST, PATH_TO_COMPANY_DATA, "token=" + TOKEN);

        ResponseEntity<Company> response =
                restTemplate.getForEntity(String.format(url.toString(), symbol), Company.class);
        return response.getBody();
    }


}
