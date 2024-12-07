package me.utku.springbank.controller;

import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.service.accountstock.AccountStockCrudService;
import me.utku.springbank.service.accountstock.AccountStockQueryService;
import me.utku.springbank.service.accountstock.AccountStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account-stock")
public class AccountStockController extends CrudController<AccountStock> {
    private final AccountStockQueryService accountStockQueryService;
    private final AccountStockService accountStockService;

    public AccountStockController(AccountStockCrudService accountStockCrudService, AccountStockQueryService accountStockQueryService, AccountStockService accountStockService) {
        super(accountStockCrudService);
        this.accountStockQueryService = accountStockQueryService;
        this.accountStockService = accountStockService;
    }

    @GetMapping("/me")
    public List<AccountStockDto> getAccountStocks(UUID accountId) {
        return accountStockQueryService.getAccountStocks(accountId);
    }

    @GetMapping("/me/{accountId}/{stockId}")
    public AccountStockDto getAccountStock(@PathVariable UUID accountId, @PathVariable UUID stockId) {
        return accountStockQueryService.getAccountStock(accountId, stockId);
    }

    @PostMapping("/me")
    public ResponseEntity<GenericResponse<AccountStockDto>> buyAccountStock(AccountStockDto accountStockDto) {
        return accountStockService.buyAccountStock(accountStockDto).toResponseEntity();
    }

    @PutMapping("/me")
    public ResponseEntity<GenericResponse<AccountStockDto>> sellAccountStock(AccountStockDto accountStockDto) {
        return accountStockService.sellAccountStock(accountStockDto).toResponseEntity();
    }
}
