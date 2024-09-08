package me.utku.springbank.account;

import me.utku.springbank.account.service.AccountCreateService;
import me.utku.springbank.account.service.AccountCrudService;
import me.utku.springbank.account.service.AccountReadService;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends CrudController<Account> {
    private final AccountReadService accountReadService;
    private final AccountCreateService accountCreateService;

    public AccountController(AccountCrudService accountCrudService, AccountReadService accountReadService, AccountCreateService accountCreateService) {
        super(accountCrudService);
        this.accountReadService = accountReadService;
        this.accountCreateService = accountCreateService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<AccountDto> getUserAccounts(@AuthenticationPrincipal User user) {
        return accountReadService.getUserAccounts(user.getId());
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> createAccountForUser(@AuthenticationPrincipal User user, @RequestBody AccountDto account) {
        return accountCreateService.createAccountForUser(user, account).toResponseEntity();
    }
}
