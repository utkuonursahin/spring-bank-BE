package me.utku.user;

public record NewUserRequest(
        String name,
        String email,
        String password,
        Integer balance,
        Integer debt) {
}
