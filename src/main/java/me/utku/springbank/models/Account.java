package me.utku.springbank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
public class Account extends BaseEntity {
    @Column(unique = true)
    private long accountNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    private BigDecimal cash;

    public Account() {
    }
}
