package ru.ersted.asyncmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stock")
@Entity
public class StockEntity extends BaseEntity {

    private String companyName;
    private Long previousVolume;
    private Long volume;
    private Double latestPrice;
    private Double profitability;
}
