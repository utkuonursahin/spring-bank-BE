package me.utku.springbank.account.service.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.Account;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.AccountMapper;
import me.utku.springbank.account.AccountRepository;
import me.utku.springbank.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateAccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto createAccountForUser(User user) {
        Account account = Account.builder()
                .owner(user)
                .cash(BigDecimal.valueOf(0.0))
                .build();
        return accountMapper.toDto(accountRepository.save(account));
    }
}