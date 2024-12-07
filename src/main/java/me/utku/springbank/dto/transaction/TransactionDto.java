package me.utku.springbank.dto.transaction;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.enums.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        TransactionType type,
        AccountDto sender,
        AccountDto receiver,
        BigDecimal amount
) implements BaseDto<Transaction> {
}
