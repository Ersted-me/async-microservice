package ru.ersted.asyncmicroservice.mapper;

import ru.ersted.asyncmicroservice.dto.StockDto;
import ru.ersted.asyncmicroservice.entity.StockEntity;

public class StockMapper {
    public static StockDto toDto(StockEntity entity){
        return StockDto.builder()
                .previousVolume(entity.getPreviousVolume())
                .volume(entity.getVolume())
                .latestPrice(entity.getLatestPrice())
                .build();
    }
    public static StockEntity toEntity(StockDto dto){
        return StockEntity.builder()
                .previousVolume(dto.getPreviousVolume())
                .volume(dto.getVolume())
                .latestPrice(dto.getLatestPrice())
                .build();
    }
}
