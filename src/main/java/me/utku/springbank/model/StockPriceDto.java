package me.utku.springbank.model;

import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.BaseDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link StockPrice}
 */
public record StockPriceDto(UUID id, Instant updatedAt, StockDto stock,
                            BigDecimal stockPrice) implements BaseDto<StockPrice> {
}