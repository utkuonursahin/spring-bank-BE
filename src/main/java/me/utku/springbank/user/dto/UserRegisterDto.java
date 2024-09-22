package me.utku.springbank.user.dto;

public record UserRegisterDto(
        String firstName,
        String lastName,
        String ssn,
        String password
) {
}
