package me.utku.springbank.mapper;

import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.AccountStock;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true))
public interface AccountStockMapper extends BaseMapper<AccountStock> {
    AccountStock toEntity(AccountStockDto accountStockDto);

    AccountStockDto toDto(AccountStock accountStock);

    AccountStock updateEntity(@MappingTarget AccountStock oldEntity, AccountStock newEntity);
}