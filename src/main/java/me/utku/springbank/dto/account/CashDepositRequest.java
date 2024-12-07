package me.utku.springbank.dto.account;

import java.math.BigDecimal;
import java.util.UUID;

public record CashDepositRequest(UUID userId, BigDecimal amountToDeposit) {
}
