package me.utku.springbank.dto.stock;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link me.utku.springbank.model.Stock}
 */
public record StockDto(UUID id, String stockCode, String stockName) implements Serializable {
}