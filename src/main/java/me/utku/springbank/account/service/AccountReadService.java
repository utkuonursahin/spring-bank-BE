package me.utku.springbank.account.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.AccountMapper;
import me.utku.springbank.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountReadService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> getUserAccounts(UUID userId) {
        return accountRepository.findAllByOwner_Id(userId).stream().map(accountMapper::toDto).toList();
    }
}
