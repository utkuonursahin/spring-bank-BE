package me.utku.springbank.dto.user;

import me.utku.springbank.enums.auth.Role;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.User;

import java.util.Set;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String ssn,
        Set<Role> authorities
) implements BaseDto<User> {
}
