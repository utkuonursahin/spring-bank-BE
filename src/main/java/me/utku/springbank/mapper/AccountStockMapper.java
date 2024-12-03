package me.utku.springbank.mapper;

import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.Account;
import me.utku.springbank.model.AccountStock;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountStockMapper extends BaseMapper<Account> {
    AccountStock toEntity(AccountStockDto accountStockDto);

    AccountStockDto toDto(AccountStock accountStock);

    Account updateEntity(@MappingTarget Account oldEntity, Account newEntity);
}