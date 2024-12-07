package me.utku.springbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.generic.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class Card extends BaseEntity {
    @Column(unique = true)
    private String cardNumber;
    private short pin;
    @OneToOne
    private User owner;
    private BigDecimal termExpense;
    private BigDecimal cardLimit;
    private boolean isOpenToInternet;
    private boolean isOpenToInternationalTransactions;
    private boolean isOpenToContactlessPayments;

    public Card(String cardNumber, short pin, User owner, BigDecimal termExpense, BigDecimal cardLimit, boolean isOpenToInternet, boolean isOpenToInternationalTransactions, boolean isOpenToContactlessPayments) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.owner = owner;
        this.termExpense = termExpense;
        this.cardLimit = cardLimit;
        this.isOpenToInternet = isOpenToInternet;
        this.isOpenToInternationalTransactions = isOpenToInternationalTransactions;
        this.isOpenToContactlessPayments = isOpenToContactlessPayments;
    }

    public Card() {
    }

    @PrePersist
    private void generateCardNumber() {
        if (this.cardNumber == null) {
            this.cardNumber = String.format("%016d", Math.abs(UUID.randomUUID().getMostSignificantBits()) % 1_000_000_000_000_000L);
        }
    }
}
