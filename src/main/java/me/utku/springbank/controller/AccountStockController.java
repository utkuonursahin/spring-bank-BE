package me.utku.springbank.controller;

import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.accountstock.AccountStockQueryService;
import me.utku.springbank.service.accountstock.AccountStockService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<AccountStockDto> getAccountStocks(UUID accountId) {
        return accountStockQueryService.getAccountStocks(accountId);
    }

    @GetMapping("/{accountId}/{stockId}")
    public AccountStockDto getAccountStock(@PathVariable UUID accountId, @PathVariable UUID stockId) {
        return accountStockQueryService.getAccountStock(accountId, stockId);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<AccountStockDto>> buyAccountStock(AccountStockDto accountStockDto) {
        return accountStockService.buyAccountStock(accountStockDto).toResponseEntity();
    }
}
