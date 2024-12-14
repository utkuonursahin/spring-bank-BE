package me.utku.springbank.dto.stock;

import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.Stock;

import java.util.UUID;

/**
 * DTO for {@link me.utku.springbank.model.Stock}
 */
public record StockDto(UUID id, String stockCode, String stockName) implements BaseDto<Stock> {
}