package ru.ersted.asyncmicroservice.service.util;

import ru.ersted.asyncmicroservice.model.iex.Company;

import java.util.List;
public class CompanyViewService {
    public static void viewAllCompaniesData(List<Company> companies) {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "Volume", "PreviousVolume", "LatestPrice", "PercentCost","CompanyName");

        companies.forEach(company ->
                System.out.printf("%-10s %-14s %-11s %-11s %-10s\n",
                        company.getVolume(),
                        company.getPreviousVolume(),
                        company.getLatestPrice(),
                        company.getPercentCost(),
                        company.getCompanyName()));
    }

    public static void viewMostExpensiveCompanies(List<Company> companies) {
        System.out.println("The Most Expensive Companies");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "Volume", "PreviousVolume", "LatestPrice", "PercentCost","CompanyName");

        companies.forEach(company ->
                System.out.printf("%-10s %-14s %-11s %-11s %-10s\n",
                        company.getVolume(),
                        company.getPreviousVolume(),
                        company.getLatestPrice(),
                        company.getPercentCost(),
                        company.getCompanyName()));

        System.out.println();
    }
}
