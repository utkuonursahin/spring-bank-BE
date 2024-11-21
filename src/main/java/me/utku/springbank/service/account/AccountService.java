package me.utku.springbank.service.account;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.account.action.CashTransferService;
import me.utku.springbank.service.account.action.CreateAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CashTransferService cashTransferService;
    private final CreateAccountService createAccountService;

    public void transferMoneyBetweenAccounts(UUID senderId, UUID receiverId, BigDecimal amount) {
        cashTransferService.issueCashTransfer(senderId, receiverId, amount);
    }

    public GenericResponse<AccountDto> createAccountForUser(User user) {
        AccountDto accountDto = createAccountService.createAccountForUser(user);
        return GenericResponse.ok(HttpStatus.CREATED.value(), accountDto);
    }
}
