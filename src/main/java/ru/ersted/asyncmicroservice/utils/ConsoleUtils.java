package ru.ersted.asyncmicroservice.utils;

import ru.ersted.asyncmicroservice.entity.StockEntity;
import ru.ersted.asyncmicroservice.entity.SymbolEntity;

import java.util.Arrays;
import java.util.List;
public class ConsoleUtils {

    public static void viewAllCompaniesData(List<StockEntity> companies, String title) {
        System.out.println(title);
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                "Volume", "PreviousVolume", "LatestPrice", "Profitability","CompanyName");

        companies.forEach(company ->
                System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                        company.getVolume(),
                        company.getPreviousVolume(),
                        company.getLatestPrice(),
                        company.getProfitability(),
                        company.getCompanyName()));
        
        System.out.println();
    }
}
