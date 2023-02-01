package ru.ersted.asyncmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symbol {
    private Long id;
    private String symbol;
    private String name;
    private String region;
    private Boolean isEnabled;

}
