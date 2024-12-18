package me.utku.springbank.service.account;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashDepositRequest;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.dto.account.CashWithdrawRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.Account;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.service.account.action.CashDepositAction;
import me.utku.springbank.service.account.action.CashTransferAction;
import me.utku.springbank.service.account.action.CashWithdrawAction;
import me.utku.springbank.service.account.action.CreateAccountAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final CashTransferAction cashTransferAction;
    private final CreateAccountAction createAccountAction;
    private final AccountRepository accountRepository;
    private final CashDepositAction cashDepositAction;
    private final CashWithdrawAction cashWithdrawAction;

    public GenericResponse<AccountDto> createAccountForUser(User user, AccountDto account) {
        if (accountRepository.existsByOwner_IdAndAccountType(user.getId(), account.accountType())) {
            throw new OperationDeniedException("Account already exists for this user");
        }
        AccountDto accountDto = createAccountAction.execute(user, account);
        return GenericResponse.ok(HttpStatus.CREATED.value(), accountDto);
    }

    public GenericResponse<AccountDto> transferCash(CashTransferRequest cashTransferRequest) {
        AccountDto account = cashTransferAction.execute(cashTransferRequest);
        return GenericResponse.ok(HttpStatus.OK.value(), account);
    }

    public void increaseCash(UUID accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        account.setCash(account.getCash().add(amount));
        accountRepository.save(account);
    }

    public void decreaseCash(UUID accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        account.setCash(account.getCash().subtract(amount));
        accountRepository.save(account);
    }

    public GenericResponse<AccountDto> depositCash(UUID userId, CashDepositRequest cashDepositRequest) {
        AccountDto account = cashDepositAction.execute(userId, cashDepositRequest);
        return GenericResponse.ok(HttpStatus.OK.value(), account);
    }

    public GenericResponse<AccountDto> withdrawCash(UUID userId, CashWithdrawRequest cashWithdrawRequest) {
        AccountDto account = cashWithdrawAction.execute(userId, cashWithdrawRequest);
        return GenericResponse.ok(HttpStatus.OK.value(), account);
    }
}
