package me.utku.springbank.account.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.AccountMapper;
import me.utku.springbank.account.AccountRepository;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountReadService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> getUserAccounts(UUID userId) {
        return accountRepository.findAllByOwner_Id(userId).stream().map(accountMapper::toDto).toList();
    }

    public AccountDto getAccount(UUID accountId) {
        return accountMapper.toDto(accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new));
    }
}
