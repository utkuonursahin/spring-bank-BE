package me.utku.springbank.service.account;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.Account;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.service.account.action.CashTransferAction;
import me.utku.springbank.service.account.action.CreateAccountAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CashTransferAction cashTransferAction;
    private final CreateAccountAction createAccountAction;
    private final AccountRepository accountRepository;

    public GenericResponse<AccountDto> createAccountForUser(User user) {
        AccountDto accountDto = createAccountAction.execute(user);
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
}
