package me.utku.springbank.dto.account;

import java.math.BigDecimal;

public record CashWithdrawRequest(BigDecimal amountToWithdraw) {
}
