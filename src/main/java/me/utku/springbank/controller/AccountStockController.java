package me.utku.springbank.controller;

import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.accountstock.AccountStockQueryService;
import me.utku.springbank.service.accountstock.AccountStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account-stock")
public class AccountStockController {
    private final AccountStockQueryService accountStockQueryService;
    private final AccountStockService accountStockService;

    public AccountStockController(AccountStockQueryService accountStockQueryService, AccountStockService accountStockService) {
        this.accountStockQueryService = accountStockQueryService;
        this.accountStockService = accountStockService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<AccountStockDto> getAccountStocks(@AuthenticationPrincipal User user) {
        return accountStockQueryService.getAccountStocks(user.getId());
    }

    @GetMapping("/me/{stockId}")
    @PreAuthorize("hasRole('USER')")
    public AccountStockDto getAccountStock(@AuthenticationPrincipal User user, @PathVariable UUID stockId) {
        return accountStockQueryService.getAccountStock(user.getId(), stockId);
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountStockDto>> buyAccountStock(@AuthenticationPrincipal User user, @RequestBody AccountStockDto accountStockDto) {
        return accountStockService.buyAccountStock(user.getId(), accountStockDto).toResponseEntity();
    }

    @DeleteMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<AccountStockDto>> sellAccountStock(@AuthenticationPrincipal User user, @RequestBody AccountStockDto accountStockDto) {
        return accountStockService.sellAccountStock(user.getId(), accountStockDto).toResponseEntity();
    }
}
