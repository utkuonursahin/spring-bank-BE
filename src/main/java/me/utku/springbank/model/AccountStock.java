package me.utku.springbank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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
    @Transient
    @JsonInclude
    private BigDecimal value;

    public AccountStock(Account account, Stock stock, BigDecimal quantity, BigDecimal value) {
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
        this.value = value;
    }

    public AccountStock() {
    }
}
