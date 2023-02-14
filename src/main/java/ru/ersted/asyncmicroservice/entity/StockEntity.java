package ru.ersted.asyncmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stock")
public class StockEntity extends BaseEntity{
    private Long previousVolume;
    private Long volume;
    private Double latestPrice;

}
