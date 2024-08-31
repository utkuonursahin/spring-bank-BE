package me.utku.springbank.transaction;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.transaction.service.TransactionCrudService;
import me.utku.springbank.transaction.service.TransactionReadService;
import me.utku.springbank.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public List<TransactionDto> getTransactionsBySender(UUID senderId) {
        return transactionReadService.getTransactionsBySender(senderId);
    }

    @GetMapping("/sender-owner")
    public List<TransactionDto> getTransactionsBySenderOwner(UUID senderOwnerId) {
        return transactionReadService.getTransactionsBySenderOwner(senderOwnerId);
    }

    @GetMapping("/me")
    public List<TransactionDto> getTransactionsBySenderOwner(@AuthenticationPrincipal User user) {
        return transactionReadService.getTransactionsBySenderOwner(user.getId());
    }
}