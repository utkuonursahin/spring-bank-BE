package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.dto.account.CashWithdrawRequest;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.mapper.AccountMapper;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.service.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashWithdrawAction {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;

    public AccountDto execute(UUID userId, CashWithdrawRequest cashWithdrawRequest) {
        Account account = accountRepository.findByOwner_IdAndAccountType(userId, AccountType.CURRENT).orElseThrow(EntityNotFoundException::new);
        account.setCash(account.getCash().subtract(cashWithdrawRequest.amountToWithdraw()));
        transactionService.createWithdrawTransactionForUser(new CashTransferRequest(null, accountMapper.toDto(account), accountMapper.toDto(account), cashWithdrawRequest.amountToWithdraw()));
        return accountMapper.toDto(accountRepository.save(account));
    }
}
