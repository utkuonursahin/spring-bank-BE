package me.utku.springbank.controller;

import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashDepositRequest;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.dto.account.CashWithdrawRequest;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.account.AccountQueryService;
import me.utku.springbank.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountQueryService accountQueryService;
    private final AccountService accountService;

    public AccountController(AccountQueryService accountQueryService, AccountService accountService) {
        this.accountQueryService = accountQueryService;
        this.accountService = accountService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<AccountDto> getUserAccounts(@AuthenticationPrincipal User user) {
        return accountQueryService.getUserAccounts(user.getId());
    }

    @GetMapping("/me/{accountType}")
    @PreAuthorize("hasRole('USER')")
    public AccountDto getUserAccountByType(@AuthenticationPrincipal User user, @PathVariable AccountType accountType) {
        return accountQueryService.getUserAccountByType(user.getId(), accountType);
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> createAccountForUser(@AuthenticationPrincipal User user, @RequestBody AccountDto account) {
        return accountService.createAccountForUser(user, account).toResponseEntity();
    }

    @PostMapping("/me/transfer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> transferAccountCash(@RequestBody CashTransferRequest cashTransferRequest) {
        return accountService.transferCash(cashTransferRequest).toResponseEntity();
    }

    @PostMapping("/me/deposit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> depositAccountCash(@AuthenticationPrincipal User user, @RequestBody CashDepositRequest cashDepositRequest) {
        return accountService.depositCash(user.getId(), cashDepositRequest).toResponseEntity();
    }

    @PostMapping("/me/withdraw")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountDto>> withdrawAccountCash(@AuthenticationPrincipal User user, @RequestBody CashWithdrawRequest cashWithdrawRequest) {
        return accountService.withdrawCash(user.getId(), cashWithdrawRequest).toResponseEntity();
    }
}
