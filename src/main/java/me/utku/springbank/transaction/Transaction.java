package me.utku.springbank.transaction;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.account.Account;
import me.utku.springbank.generic.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class Transaction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;
    private BigDecimal amount;

    public Transaction(TransactionType type, Account sender, Account receiver, BigDecimal amount) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Transaction() {
    }
}
