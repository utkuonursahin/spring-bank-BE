package me.utku.springbank.transaction;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.transaction.dto.TransactionPageDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true))
public interface TransactionMapper extends BaseMapper<Transaction> {
    TransactionDto toDto(Transaction transaction);

    Transaction toEntity(TransactionDto transactionDto);

    Transaction updateEntity(@MappingTarget Transaction oldEntity, Transaction newEntity);

    @Mapping(target = "transactions", source = "content")
    TransactionPageDto toPageDto(Page<Transaction> page);
}
