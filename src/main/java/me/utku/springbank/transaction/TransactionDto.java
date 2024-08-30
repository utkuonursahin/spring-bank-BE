package me.utku.springbank.transaction;

import me.utku.springbank.account.AccountDto;

import java.math.BigDecimal;

public record TransactionDto(
        TransactionType transactionType,
        AccountDto sender,
        AccountDto receiver,
        BigDecimal amount
) {
}
