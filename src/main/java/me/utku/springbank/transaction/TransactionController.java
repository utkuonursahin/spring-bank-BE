package me.utku.springbank.transaction;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.transaction.service.TransactionCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController extends CrudController<Transaction> {

    public TransactionController(TransactionCrudService transactionCrudService) {
        super(transactionCrudService);
    }
}