package ru.ersted.asyncmicroservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyDto {
    @Id
    private Long id;
    private String companyName;
    private Long volume;
    private Long previousVolume;

    private Double latestPrice;
}
