package ru.ersted.asyncmicroservice.entity;

import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "symbol")
public class SymbolEntity extends BaseEntity {
    private String symbol;
    private String name;
    private Boolean isEnabled;
}
