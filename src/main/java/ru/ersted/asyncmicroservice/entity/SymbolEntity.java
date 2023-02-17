package ru.ersted.asyncmicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "symbol")
@Entity
public class SymbolEntity extends BaseEntity {
    private String symbol;
    private String name;
    private Boolean isEnabled;
}
