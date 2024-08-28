package me.utku.springbank.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.transaction.TransactionType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Transaction extends BaseEntity{
    private TransactionType type;
    private User sender;
    private User receiver;
    private BigDecimal amount;

    public Transaction(TransactionType type, User sender, User receiver, BigDecimal amount) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Transaction() {}
}
