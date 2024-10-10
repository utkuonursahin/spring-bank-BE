package me.utku.springbank.transaction.dto;

import me.utku.springbank.account.AccountDto;
import me.utku.springbank.transaction.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionCreateDto(
        AccountDto receiver,
        TransactionType transactionType,
        BigDecimal amount
) {
}
