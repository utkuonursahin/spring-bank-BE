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

    public GenericResponse<AccountDto> createAccountForUser(User user, AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account.setOwner(user);
        account.setCash(BigDecimal.valueOf(0.0));
        account = accountRepository.save(account);
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Account created successfully", accountMapper.toDto(account));
    }
}
