package ru.ersted.asyncmicroservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ersted.asyncmicroservice.service.CompanyService;


//@Slf4j
//@Component
//@RequiredArgsConstructor
public class ProcessCompaniesJob {

    //private final CompanyService companyService;

//    @Scheduled(cron = "0 0 0 0 0 0")
//    public void processStocks() {
//        companyService.processCompanies();
//    }

}
