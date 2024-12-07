package me.utku.springbank.service.transaction;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.service.transaction.action.CreateTransferTransactionAction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CreateTransferTransactionAction createTransferTransactionAction;

    public void createTransferTransactionForUser(CashTransferRequest cashTransferRequest) {
        createTransferTransactionAction.execute(cashTransferRequest);
    }
}
