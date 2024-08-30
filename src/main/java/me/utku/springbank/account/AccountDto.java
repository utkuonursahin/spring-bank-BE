package me.utku.springbank.account;

import me.utku.springbank.user.UserDto;

import java.math.BigDecimal;

public record AccountDto(
        long accountNumber,
        UserDto owner,
        BigDecimal cash
) {
}
