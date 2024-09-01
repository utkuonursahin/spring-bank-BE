package me.utku.springbank.transaction.dto;

import me.utku.springbank.account.AccountDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.TransactionType;

import java.math.BigDecimal;

public record TransactionDto(
        TransactionType transactionType,
        AccountDto sender,
        AccountDto receiver,
        BigDecimal amount
) implements BaseDto<Transaction> {
}
