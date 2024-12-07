package me.utku.springbank.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.generic.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class Account extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    private BigDecimal cash;

    public Account(AccountType accountType, User owner, BigDecimal cash) {
        this.accountType = accountType;
        this.owner = owner;
        this.cash = cash;
    }

    public Account() {
    }
}
