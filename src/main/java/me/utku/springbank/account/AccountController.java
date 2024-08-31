package me.utku.springbank.account;

import me.utku.springbank.account.service.AccountCrudService;
import me.utku.springbank.generic.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends CrudController<Account> {
    
    public AccountController(AccountCrudService accountCrudService) {
        super(accountCrudService);
    }
}
