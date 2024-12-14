package me.utku.springbank.mapper;

import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.Stock;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper extends BaseMapper<Stock> {
    Stock toEntity(StockDto stockDto);

    StockDto toDto(Stock stock);

    Stock updateEntity(@MappingTarget Stock oldEntity, Stock newEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Stock partialUpdate(StockDto stockDto, @MappingTarget Stock stock);
}