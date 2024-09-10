package me.utku.springbank.transaction;

import me.utku.springbank.account.AccountDto;
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

    @GetMapping("/sender")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getTransactionsBySender(UUID senderId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySender(senderId, page, size);
    }

    @GetMapping("/sender-owner")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionPageDto getTransactionsBySenderOwner(UUID senderOwnerId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySenderOwner(senderOwnerId, page, size);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactions(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySenderOwner(user.getId(), page, size);
    }

    @GetMapping("/me/sender")
    @PreAuthorize("hasRole('USER')")
    public TransactionPageDto getUserTransactionsSentByUserAccount(@AuthenticationPrincipal User user, AccountDto account, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsSentByUserAccount(user, account, page, size);
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<TransactionDto>> createTransactionForUser(@AuthenticationPrincipal User user, @RequestBody TransactionDto transaction) {
        return transactionCreateService.createTransactionForUser(user, transaction).toResponseEntity();
    }
}