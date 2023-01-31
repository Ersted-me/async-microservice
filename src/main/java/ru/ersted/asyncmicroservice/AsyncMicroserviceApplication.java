package ru.ersted.asyncmicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.ersted.asyncmicroservice.api.IEXApi;
import ru.ersted.asyncmicroservice.dto.CompanyDto;
import ru.ersted.asyncmicroservice.dto.SymbolDto;

import java.util.List;

@SpringBootApplication
public class AsyncMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncMicroserviceApplication.class, args);

        IEXApi api = new IEXApi();
        List<SymbolDto> symbolsList = api.getSymbolsList();
        //System.out.println(symbolsList);
        for (SymbolDto symbol: symbolsList) {
            System.out.println(api.getCompanyDataBySymbol(symbol.getSymbol()));
        }

    }

}
