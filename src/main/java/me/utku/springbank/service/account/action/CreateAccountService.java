package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.model.Account;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.model.User;
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