package me.utku.springbank.account;

import me.utku.springbank.account.service.AccountCrudService;
import me.utku.springbank.account.service.AccountReadService;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends CrudController<Account> {
    private final AccountReadService accountReadService;

    public AccountController(AccountCrudService accountCrudService, AccountReadService accountReadService) {
        super(accountCrudService);
        this.accountReadService = accountReadService;
    }

    @GetMapping("/me")
    public List<AccountDto> getUserAccounts(@AuthenticationPrincipal User user) {
        return accountReadService.getUserAccounts(user.getId());
    }
}
