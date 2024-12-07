package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashDepositRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashDepositAction {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto execute(CashDepositRequest cashDepositRequest) {
        Account account = accountRepository.findById(cashDepositRequest.userId()).orElseThrow(EntityNotFoundException::new);
        account.setCash(account.getCash().add(cashDepositRequest.amountToDeposit()));
        return accountMapper.toDto(accountRepository.save(account));
    }
}
