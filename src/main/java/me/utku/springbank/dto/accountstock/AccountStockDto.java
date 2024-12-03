package me.utku.springbank.dto.accountstock;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.stock.StockDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link me.utku.springbank.model.AccountStock}
 */
public record AccountStockDto(UUID id, AccountDto account, StockDto stock,
                              BigDecimal quantity) implements Serializable {
}