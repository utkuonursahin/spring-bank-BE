package me.utku.springbank.account.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.Account;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.AccountMapper;
import me.utku.springbank.account.AccountRepository;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountCreateService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public GenericResponse<AccountDto> createAccountForUser(User user) {
        Account account = Account.builder()
                .owner(user)
                .cash(BigDecimal.valueOf(0.0))
                .build();
        account = accountRepository.save(account);
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Account created successfully", accountMapper.toDto(account));
    }
}
