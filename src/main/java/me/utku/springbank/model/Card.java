package me.utku.springbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.generic.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class Card extends BaseEntity {
    private String cardNumber;
    private short pin;
    @OneToOne
    private User owner;
    private BigDecimal cardLimit;
    private boolean isOpenToInternet;
    private boolean isOpenToInternationalTransactions;
    private boolean isOpenToContactlessPayments;

    public Card(String cardNumber, short pin, User owner, BigDecimal cardLimit, boolean isOpenToInternet, boolean isOpenToInternationalTransactions, boolean isOpenToContactlessPayments) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.owner = owner;
        this.cardLimit = cardLimit;
        this.isOpenToInternet = isOpenToInternet;
        this.isOpenToInternationalTransactions = isOpenToInternationalTransactions;
        this.isOpenToContactlessPayments = isOpenToContactlessPayments;
    }

    public Card() {

    }
}
