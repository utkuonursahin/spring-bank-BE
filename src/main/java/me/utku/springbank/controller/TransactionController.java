package me.utku.springbank.controller;

import me.utku.springbank.dto.transaction.TransactionPageDto;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.model.User;
import me.utku.springbank.service.transaction.TransactionCrudService;
import me.utku.springbank.service.transaction.TransactionQueryService;
import me.utku.springbank.service.transaction.TransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController extends CrudController<Transaction> {
    private final TransactionQueryService transactionQueryService;
    private final TransactionService transactionService;

    public TransactionController(TransactionCrudService transactionCrudService, TransactionQueryService transactionQueryService, TransactionService transactionService) {
        super(transactionCrudService);
        this.transactionQueryService = transactionQueryService;
        this.transactionService = transactionService;
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getAccountTransactions(UUID accountId, @RequestParam int page, @RequestParam int size) {
        return transactionQueryService.getAccountTransactions(accountId, page, size);
    }

    @GetMapping("/account-owner")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getAccountOwnerTransactions(UUID ownerId, @RequestParam int page, @RequestParam int size) {
        return transactionQueryService.getAccountOwnerTransactions(ownerId, page, size);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactions(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionQueryService.getUserTransactions(user, page, size);
    }

    @GetMapping("/me/sender")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactionsSentByUser(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionQueryService.getTransactionsSentByUser(user, page, size);
    }

    @GetMapping("/me/receiver")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactionsReceivedByUser(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionQueryService.getTransactionsReceivedByUser(user, page, size);
    }
}