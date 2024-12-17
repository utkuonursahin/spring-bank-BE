package me.utku.springbank.service.account;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> getUserAccounts(UUID userId) {
        return accountRepository.findAllByOwner_Id(userId).stream().map(accountMapper::toDto).toList();
    }

    public AccountDto getUserAccountByType(UUID userId, AccountType accountType) {
        return accountMapper.toDto(accountRepository.findByOwner_IdAndAccountType(userId, accountType).orElseThrow(EntityNotFoundException::new));
    }

    public AccountDto getAccount(UUID accountId) {
        return accountMapper.toDto(accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new));
    }
}
