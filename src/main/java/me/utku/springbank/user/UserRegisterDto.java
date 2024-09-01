package me.utku.springbank.user;

public record UserRegisterDto(
        String firstName,
        String lastName,
        String ssn,
        String password
) {
}
