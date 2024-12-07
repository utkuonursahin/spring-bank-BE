package me.utku.springbank.dto.accountstock;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.AccountStock;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link me.utku.springbank.model.AccountStock}
 */
public record AccountStockDto(UUID id,
                              AccountDto account,
                              StockDto stock,
                              BigDecimal quantity,
                              BigDecimal value
) implements BaseDto<AccountStock> {

}