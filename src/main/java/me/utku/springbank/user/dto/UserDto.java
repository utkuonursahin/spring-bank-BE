package me.utku.springbank.user.dto;

import me.utku.springbank.auth.Role;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.user.User;

import java.util.Set;

public record UserDto(
        String firstName,
        String lastName,
        String ssn,
        Set<Role> authorities
) implements BaseDto<User> {
}
