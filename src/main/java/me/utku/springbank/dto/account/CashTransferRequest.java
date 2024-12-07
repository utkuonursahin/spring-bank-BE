package me.utku.springbank.dto.account;

import java.math.BigDecimal;
import java.util.UUID;

public record CashTransferRequest(UUID id,
                                  AccountDto sender,
                                  AccountDto receiver,
                                  BigDecimal amount) {
}
