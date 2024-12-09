package me.utku.springbank.mapper;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.StockPrice;
import me.utku.springbank.model.StockPriceDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true))
public interface StockPriceMapper extends BaseMapper<StockPrice> {
    StockPrice toEntity(StockPriceDto stockPriceDto);

    StockPriceDto toDto(StockPrice stockPrice);

    StockPrice updateEntity(@MappingTarget StockPrice oldEntity, StockPrice newEntity);
}