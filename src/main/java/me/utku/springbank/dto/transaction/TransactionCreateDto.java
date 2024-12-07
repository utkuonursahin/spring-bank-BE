package me.utku.springbank.dto.transaction;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.enums.transaction.TransactionType;

import java.math.BigDecimal;

public record TransactionCreateDto(
        AccountDto receiver,
        TransactionType transactionType,
        BigDecimal amount
) {
}
