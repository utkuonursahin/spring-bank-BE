package me.utku.springbank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    @Column(unique = true)
    private long accountId;
    @ManyToOne
    private User owner;
}
