package me.utku.springbank.dto.user;

public record UserRegisterDto(
        String firstName,
        String lastName,
        String ssn,
        String password
) {
}
