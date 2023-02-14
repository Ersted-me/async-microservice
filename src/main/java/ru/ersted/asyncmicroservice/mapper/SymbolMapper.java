package ru.ersted.asyncmicroservice.mapper;

import ru.ersted.asyncmicroservice.dto.SymbolDto;
import ru.ersted.asyncmicroservice.entity.SymbolEntity;

public class SymbolMapper {
    public static SymbolDto toDto(SymbolEntity entity){
        return SymbolDto.builder()
                .symbol(entity.getSymbol())
                .name(entity.getName())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
    public static SymbolEntity toEntity(SymbolDto dto){
        return SymbolEntity.builder()
                .symbol(dto.getSymbol())
                .name(dto.getName())
                .isEnabled(dto.getIsEnabled())
                .build();
    }

}
