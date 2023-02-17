package ru.ersted.asyncmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.entity.SymbolEntity;
import ru.ersted.asyncmicroservice.repository.CompanyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void processCompanies() {
        List<StockEntity> all = companyRepository.findAll();
        System.out.println(all.size());
        //1. Get all companies
        //2. Using async and paral... get the data for all Stocks
        //3. Accumulate all responses in the collection
        //4. save with batches (500 - 1K - 2K)
    }
}
