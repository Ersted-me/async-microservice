package ru.ersted.asyncmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.client.IEXApiClient;
import ru.ersted.asyncmicroservice.dto.StockDto;
import ru.ersted.asyncmicroservice.mapper.StockMapper;
import ru.ersted.asyncmicroservice.repository.CompanyRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final IEXApiClient iexApiClient;


    @Override
    public void processCompanies() {

         iexApiClient.getSymbolsList()
                 .thenApply(HttpEntity::getBody)
                 .thenApply(array ->
                        {
                            List<CompletableFuture<ResponseEntity<StockDto>>> futuresTask = new ArrayList<>(array.length);
                            Arrays.stream(array)
                                    .forEach(symbol -> futuresTask.add(iexApiClient.getCompanyDataBySymbol(symbol.getSymbol())));
                            return futuresTask;
                        })
                 .thenApply(list -> list.stream()
                         .map(CompletableFuture::join)
                         .map(HttpEntity::getBody)
                         .filter(Objects::nonNull)
                         .map(StockMapper::toEntity)
                         .collect(Collectors.toList())
                 )
         ;

        //List<SymbolDto> symbolDtos = Arrays.stream(futureSymbolDto.join()).collect(Collectors.toList());

        //List<CompletableFuture<ResponseEntity<StockDto>>> futuresTask = new ArrayList<>(symbolDtos.size());

        //symbolDtos.forEach(symbol -> futuresTask.add(iexApiClient.getCompanyDataBySymbol(symbol.getSymbol())));
        /*futuresTask.stream()
                .map(CompletableFuture::join)
                .map(HttpEntity::getBody)
                .filter(Objects::nonNull)
                .map(StockMapper::toEntity)
                .collect(Collectors.toList());*/

        //1. Get all companies
        //2. Using async and paral... get the data for all Stocks
        //3. Accumulate all responses in the collection
        //4. save with batches (500 - 1K - 2K)
    }
}
