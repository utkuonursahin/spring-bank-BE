package me.utku.springbank.service.account;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.account.action.CashTransferAction;
import me.utku.springbank.service.account.action.CreateAccountAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CashTransferAction cashTransferAction;
    private final CreateAccountAction createAccountAction;

    public GenericResponse<AccountDto> createAccountForUser(User user) {
        AccountDto accountDto = createAccountAction.execute(user);
        return GenericResponse.ok(HttpStatus.CREATED.value(), accountDto);
    }

    public GenericResponse<AccountDto> transferCash(CashTransferRequest cashTransferRequest) {
        AccountDto account = cashTransferAction.execute(cashTransferRequest);
        return GenericResponse.ok(HttpStatus.OK.value(), account);
    }
}
