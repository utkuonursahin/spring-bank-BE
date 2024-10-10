package me.utku.springbank.account;

import me.utku.springbank.account.service.AccountCrudService;
import me.utku.springbank.account.service.AccountQueryService;
import me.utku.springbank.account.service.AccountService;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends CrudController<Account> {
    private final AccountQueryService accountQueryService;
    private final AccountService accountService;

    public AccountController(AccountCrudService accountCrudService, AccountQueryService accountQueryService, AccountService accountService) {
        super(accountCrudService);
        this.accountQueryService = accountQueryService;
        this.accountService = accountService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<AccountDto> getUserAccounts(@AuthenticationPrincipal User user) {
        return accountQueryService.getUserAccounts(user.getId());
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> createAccountForUser(@AuthenticationPrincipal User user) {
        return accountService.createAccountForUser(user).toResponseEntity();
    }
}
