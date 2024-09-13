package me.utku.springbank.user.dto;

public record UserRegisterDto(
        String firstName,
        String lastName,
        long ssn,
        String password
) {
}
