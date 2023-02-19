package com.utku.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String username;
    private String password;
    private Integer balance;
    private Integer debt;

    public User(int userID, String username, String password, int balance, int debt) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.debt = debt;
    }

    public User() {}
}
