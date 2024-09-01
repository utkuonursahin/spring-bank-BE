package me.utku.springbank.transaction;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.transaction.dto.TransactionPageDto;
import me.utku.springbank.transaction.service.TransactionCrudService;
import me.utku.springbank.transaction.service.TransactionReadService;
import me.utku.springbank.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController extends CrudController<Transaction> {
    private final TransactionReadService transactionReadService;

    public TransactionController(TransactionCrudService transactionCrudService, TransactionReadService transactionReadService) {
        super(transactionCrudService);
        this.transactionReadService = transactionReadService;
    }

    @GetMapping("/sender")
    public TransactionPageDto getTransactionsBySender(UUID senderId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySender(senderId, page, size);
    }

    @GetMapping("/sender-owner")
    public TransactionPageDto getTransactionsBySenderOwner(UUID senderOwnerId, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySenderOwner(senderOwnerId, page, size);
    }

    @GetMapping("/me")
    public TransactionPageDto getMyTransactions(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size) {
        return transactionReadService.getTransactionsBySenderOwner(user.getId(), page, size);
    }
}