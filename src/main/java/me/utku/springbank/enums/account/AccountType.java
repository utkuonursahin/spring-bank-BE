package me.utku.springbank.enums.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    CURRENT,
    FIXED_DEPOSIT,
    SAVINGS
}
