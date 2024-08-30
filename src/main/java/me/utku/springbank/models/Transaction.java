package me.utku.springbank.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.transaction.TransactionType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class Transaction extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;
    private BigDecimal amount;

    public Transaction() {
    }
}
