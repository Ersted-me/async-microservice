package ru.ersted.asyncmicroservice.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ersted.asyncmicroservice.service.CompanyService;

@SpringBootTest
class IEXApiClientTest {

    @Autowired
    private IEXApiClient iexApiClient;

    @Autowired
    private CompanyService companyService;

    @Test
    void getSymbolsList() throws InterruptedException {
//        CompletableFuture<SymbolDto[]> list = iexApiClient.getSymbolsList();
//        list.thenAccept(s -> Arrays.stream(s).forEach(System.out::println));
//
//        Thread.sleep(10000);
//        System.out.println("done");
        companyService.processCompanies();
    }

    @Test
    void getCompanyDataBySymbol() {
        companyService.processCompanies();

    }
}