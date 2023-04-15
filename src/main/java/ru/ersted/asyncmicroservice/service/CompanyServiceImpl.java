package ru.ersted.asyncmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.client.IEXApiClient;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.mapper.StockMapper;
import ru.ersted.asyncmicroservice.repository.StockRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final StockRepository stockRepository;
    private final IEXApiClient iexApiClient;

    @Override
    public void processCompanies() {
        iexApiClient.getSymbolsList()
                .thenApply(HttpEntity::getBody)
                .thenApply(array -> Arrays.stream(array)
                        .map(e -> iexApiClient.getCompanyDataBySymbol(e.getSymbol()))
                        .collect(Collectors.toList()))
                .thenAccept(list -> {
                    List<StockEntity> entityList = list.stream()
                            .map(CompletableFuture::join)
                            .map(HttpEntity::getBody)
                            .filter(Objects::nonNull)
                            .map(StockMapper::toEntity)
                            .collect(Collectors.toList());
                    stockRepository.saveAll(entityList);
                });
    }
}