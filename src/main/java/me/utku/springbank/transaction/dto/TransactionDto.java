package me.utku.springbank.transaction.dto;

import me.utku.springbank.account.AccountDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.enums.TransactionType;

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
