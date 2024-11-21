package me.utku.springbank.mapper;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.dto.transaction.TransactionDto;
import me.utku.springbank.dto.transaction.TransactionPageDto;
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
