package com.utku.records;

public record NewUserRequest(
        String name,
        String email,
        String password,
        Integer balance,
        Integer debt) {
}
