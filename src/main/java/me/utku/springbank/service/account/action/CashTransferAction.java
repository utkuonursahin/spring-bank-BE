package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.service.auth.AuthService;
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
    private final AuthService authService;

    public AccountDto execute(CashTransferRequest cashTransferRequest) {
        if (!checkOperationEligibility(findAccount(cashTransferRequest.sender().id()), cashTransferRequest.amount())) {
            throw new OperationDeniedException("You are not allowed to perform this operation");
        }
        AccountDto senderAccount = setAccountCash(cashTransferRequest.sender().id(), cashTransferRequest.amount(), false);
        setAccountCash(cashTransferRequest.receiver().id(), cashTransferRequest.amount(), true);
        transactionService.createTransferTransactionForUser(cashTransferRequest);
        return senderAccount;
    }

    private AccountDto setAccountCash(UUID accountId, BigDecimal amount, boolean isReceiver) {
        Account account = findAccount(accountId);
        if (isReceiver) {
            account.setCash(account.getCash().add(amount));
        } else {
            account.setCash(account.getCash().subtract(amount));
        }
        return accountMapper.toDto(accountRepository.save(account));
    }

    private Account findAccount(UUID accountId) {
        return accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
    }

    public boolean checkOperationEligibility(Account senderAccount, BigDecimal amount) {
        return authService.getAuthenticatedUser().getId().equals(senderAccount.getOwner().getId()) && senderAccount.getCash().compareTo(amount) >= 0;
    }
}
