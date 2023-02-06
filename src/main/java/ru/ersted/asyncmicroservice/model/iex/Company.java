package ru.ersted.asyncmicroservice.model.iex;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private Long volume;
    private Long previousVolume;
    private Double latestPrice;
    private Double percentCost;

}
