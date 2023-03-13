package ru.ersted.asyncmicroservice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.client.IEXApiClient;
import ru.ersted.asyncmicroservice.dto.StockDto;
import ru.ersted.asyncmicroservice.dto.SymbolDto;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.mapper.StockMapper;
import ru.ersted.asyncmicroservice.repository.CompanyRepository;
import ru.ersted.asyncmicroservice.utils.Partition;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final IEXApiClient iexApiClient;

    private final EntityManager entityManager;
    //@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int chunckSize = 1000;


    @Override
    public void processCompanies() {

        iexApiClient.getSymbolsList()
                .thenApply(HttpEntity::getBody)
                .thenApply(array -> Arrays.stream(array)
                        .map(e -> iexApiClient.getCompanyDataBySymbol(e.getSymbol()))
                        .collect(Collectors.toList()))
                .thenApply(list -> list.stream()
                        .map(re -> re.thenApply(HttpEntity::getBody))
                        .filter(Objects::nonNull)
                        .map(cDto -> cDto.thenApply(StockMapper::toEntity))
                        .collect(Collectors.toList())
                )
                .thenAccept(list -> Partition.ofSize(list, chunckSize)
                        .forEach(e -> {
                            List<StockEntity> stockEntities = e.stream()
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.toList());
                            companyRepository.saveAll(stockEntities);
                        })
                );

        //1. Get all companies
        //2. Using async and paral... get the data for all Stocks
        //3. Accumulate all responses in the collection
        //4. save with batches (500 - 1K - 2K)
    }
}