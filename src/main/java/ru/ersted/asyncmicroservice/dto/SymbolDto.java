package ru.ersted.asyncmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymbolDto {
    private Long id;
    private String symbol;
    private String name;
    private String region;
    private Boolean isEnabled;

}
