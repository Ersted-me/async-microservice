package ru.ersted.asyncmicroservice.api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ersted.asyncmicroservice.dto.CompanyDto;
import ru.ersted.asyncmicroservice.dto.SymbolDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@Component
public class IEXApi {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL_SYMBOLS =
            "";
    private final String URL_COMPANY_DATA =
            "";

    // todo: разобраться почему это говно возвращает null
    @Value("${web.token}")
    private String TOKEN;

    public List<SymbolDto> getSymbolsList(){

        String str = TOKEN;
        System.out.println(str);

        ResponseEntity<SymbolDto[]> response = restTemplate.getForEntity(URL_SYMBOLS, SymbolDto[].class);
        return response.getBody() != null? Arrays.asList(response.getBody()) : Collections.emptyList();
    }

    public CompanyDto getCompanyDataBySymbol(String symbol){
        ResponseEntity<CompanyDto> response =
                restTemplate.getForEntity(String.format(URL_COMPANY_DATA, symbol), CompanyDto.class);
        return response.getBody();
    }



}
