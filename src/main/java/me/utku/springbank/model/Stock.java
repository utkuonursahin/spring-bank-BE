package me.utku.springbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.generic.BaseEntity;

@Entity
@Getter
@Setter
@Builder
public class Stock extends BaseEntity {
    @Column(length = 3)
    private String stockCode;
    private String stockName;

    public Stock(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public Stock() {
    }
}
