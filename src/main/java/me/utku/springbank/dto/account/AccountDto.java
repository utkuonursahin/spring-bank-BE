package me.utku.springbank.dto.account;

import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.model.Account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        UUID id,
        UserDto owner,
        BigDecimal cash
) implements BaseDto<Account> {
}