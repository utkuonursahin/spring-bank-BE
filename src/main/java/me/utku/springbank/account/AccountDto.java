package me.utku.springbank.account;

import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.user.dto.UserDto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        UUID id,
        UserDto owner,
        BigDecimal cash
) implements BaseDto<Account> {
}
