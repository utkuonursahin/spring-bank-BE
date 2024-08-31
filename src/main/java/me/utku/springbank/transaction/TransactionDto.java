package me.utku.springbank.transaction;

import me.utku.springbank.account.AccountDto;
import me.utku.springbank.generic.BaseDto;

import java.math.BigDecimal;

public record TransactionDto(
        TransactionType transactionType,
        AccountDto sender,
        AccountDto receiver,
        BigDecimal amount
) implements BaseDto<Transaction> {
}
