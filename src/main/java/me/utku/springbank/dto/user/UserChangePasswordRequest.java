package me.utku.springbank.dto.user;

public record UserChangePasswordRequest(String oldPassword, String newPassword) {
}
