package ru.ersted.asyncmicroservice.service.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ersted.asyncmicroservice.api.IEXApi;
import ru.ersted.asyncmicroservice.model.iex.Company;
import ru.ersted.asyncmicroservice.model.iex.Symbol;
import ru.ersted.asyncmicroservice.service.db.impl.CompanyServiceImpl;
import ru.ersted.asyncmicroservice.service.util.CompanyViewService;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;


@Service
public class CompanyExecutor {
    private final IEXApi iexApi;
    private final CompanyServiceImpl companyService;

    private final ScheduledExecutorService service;
    @Autowired
    public CompanyExecutor(IEXApi iexApi, CompanyServiceImpl companyService) {
        this.iexApi = iexApi;
        this.companyService = companyService;

        this.service = Executors.newScheduledThreadPool(2);
        this.startToUpdateCompaniesData();
        this.toViewFiveMostExpensiveCompanies();
    }

    private void toViewFiveMostExpensiveCompanies(){
        service.scheduleWithFixedDelay(() -> {
            List<Company> mostExpensiveCompanies =
                    companyService.getMostExpensiveCompanies(5L);

            List<Company> greatestChangePercentStockValue =
                    companyService.getCompaniesGreatestChangePercentStockValue(5L);

            CompanyViewService.viewAllCompaniesData(
                    Stream
                            .concat(mostExpensiveCompanies.stream(), greatestChangePercentStockValue.stream())
                            .toList());
        },
                5,
                15,
                TimeUnit.SECONDS);
    }

    private void startToUpdateCompaniesData(){
        service.scheduleWithFixedDelay(
                this::updateCompaniesData,
                5,
                5,
                TimeUnit.SECONDS);
    }

    private void updateCompaniesData() {
        List<Symbol> symbolsList = iexApi.getSymbolsList();
        for (Symbol symbol : symbolsList) {
            System.out.println(symbol.getName());
            if (!symbol.getIsEnabled())
                continue;
            companyService.saveOrUpdate(iexApi.getCompanyDataBySymbol(symbol.getSymbol()));
        }
    }


}
