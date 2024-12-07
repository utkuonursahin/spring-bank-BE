package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.service.transaction.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CashTransferAction {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;

    public AccountDto execute(CashTransferRequest cashTransferRequest) {
        AccountDto account = findUserAndSetCash(cashTransferRequest.sender().id(), cashTransferRequest.amount(), false);
        findUserAndSetCash(cashTransferRequest.receiver().id(), cashTransferRequest.amount(), true);
        transactionService.createTransferTransactionForUser(cashTransferRequest);
        return account;
    }

    private AccountDto findUserAndSetCash(UUID userId, BigDecimal amount, boolean isReceiver) {
        Account account = accountRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (isReceiver) {
            account.setCash(account.getCash().add(amount));
        } else {
            account.setCash(account.getCash().subtract(amount));
        }
        return accountMapper.toDto(accountRepository.save(account));
    }
}
