package ru.ersted.asyncmicroservice.model.iex;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private Long id;
    private String companyName;
    private Long volume;
    private Long previousVolume;
    private Double latestPrice;
}
