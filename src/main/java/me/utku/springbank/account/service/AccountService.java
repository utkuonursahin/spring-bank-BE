package me.utku.springbank.account.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.service.action.CreateAccountService;
import me.utku.springbank.account.service.action.TransferCashService;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final TransferCashService transferCashService;
    private final CreateAccountService createAccountService;

    public void transferMoneyBetweenAccounts(UUID senderId, UUID receiverId, BigDecimal amount) {
        transferCashService.transferMoneyBetweenAccounts(senderId, receiverId, amount);
    }

    public GenericResponse<AccountDto> createAccountForUser(User user) {
        AccountDto accountDto = createAccountService.createAccountForUser(user);
        return new GenericResponse<>(HttpStatus.CREATED.value(), accountDto);
    }
}
