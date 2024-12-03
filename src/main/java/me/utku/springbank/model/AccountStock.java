package me.utku.springbank.model;

import jakarta.persistence.Entity;
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
public class AccountStock extends BaseEntity {
    @ManyToOne
    private Account account;
    @ManyToOne
    private Stock stock;
    private BigDecimal quantity;

    public AccountStock(Account account, Stock stock, BigDecimal quantity) {
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
    }

    public AccountStock() {
    }
}
