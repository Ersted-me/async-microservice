package ru.ersted.asyncmicroservice.model.iex;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Symbol {
    private Long id;
    private String symbol;
    private String name;
    private String region;
    private Boolean isEnabled;

}
