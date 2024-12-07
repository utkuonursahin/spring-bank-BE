package me.utku.springbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.generic.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class StockPrice extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;
    private BigDecimal stockPrice;

    public StockPrice(Stock stock, BigDecimal stockPrice) {
        this.stock = stock;
        this.stockPrice = stockPrice;
    }

    public StockPrice() {
    }
}
