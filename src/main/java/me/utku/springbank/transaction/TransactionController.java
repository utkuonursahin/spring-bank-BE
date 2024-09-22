package me.utku.springbank.transaction;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.transaction.dto.TransactionPageDto;
import me.utku.springbank.transaction.service.TransactionCreateService;
import me.utku.springbank.transaction.service.TransactionCrudService;
import me.utku.springbank.transaction.service.TransactionReadService;
import me.utku.springbank.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController extends CrudController<Transaction> {
    private final TransactionReadService transactionReadService;
    private final TransactionCreateService transactionCreateService;

    public TransactionController(TransactionCrudService transactionCrudService, TransactionReadService transactionReadService, TransactionCreateService transactionCreateService) {
        super(transactionCrudService);
        this.transactionReadService = transactionReadService;
        this.transactionCreateService = transactionCreateService;
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getAccountTransactions(UUID accountId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getAccountTransactions(accountId, page, size);
    }

    @GetMapping("/account-owner")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getAccountOwnerTransactions(UUID ownerId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getAccountOwnerTransactions(ownerId, page, size);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactions(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getUserTransactions(user, page, size);
    }

    @GetMapping("/me/sender")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactionsSentByUser(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsSentByUser(user, page, size);
    }

    @GetMapping("/me/receiver")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactionsReceivedByUser(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsReceivedByUser(user, page, size);
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<TransactionDto>> createTransactionForUser(@AuthenticationPrincipal User user, @RequestBody TransactionDto transactionRequest) {
        return transactionCreateService.createTransactionForUser(user, transactionRequest).toResponseEntity();
    }
}