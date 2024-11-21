package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.model.Account;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashDepositService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto performDeposit(UUID userId, BigDecimal amountToDeposit) {
        Account account = accountRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        account.setCash(account.getCash().add(amountToDeposit));
        return accountMapper.toDto(accountRepository.save(account));
    }
}
