package me.utku.springbank.user;

import me.utku.springbank.auth.Role;

import java.util.Set;

public record UserDto(
        String firstName,
        String lastName,
        String ssn,
        Set<Role> authorities
) {
}