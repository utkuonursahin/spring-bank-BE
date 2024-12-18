package me.utku.springbank.dto.account;

import java.math.BigDecimal;

public record CashDepositRequest(BigDecimal amountToDeposit) {
}
