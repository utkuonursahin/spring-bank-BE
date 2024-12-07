package me.utku.springbank.mapper;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.Account;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true))
public interface AccountMapper extends BaseMapper<Account> {
    AccountDto toDto(Account entity);

    Account toEntity(AccountDto dto);

    Account updateEntity(@MappingTarget Account oldEntity, Account newEntity);
}
