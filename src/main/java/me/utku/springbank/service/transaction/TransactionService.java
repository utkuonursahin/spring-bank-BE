package me.utku.springbank.service.transaction;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.service.transaction.action.CreateDepositTransactionAction;
import me.utku.springbank.service.transaction.action.CreateTransferTransactionAction;
import me.utku.springbank.service.transaction.action.CreateWithdrawTransactionAction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CreateTransferTransactionAction createTransferTransactionAction;
    private final CreateDepositTransactionAction createDepositTransactionAction;
    private final CreateWithdrawTransactionAction createWithdrawTransactionAction;

    public void createTransferTransactionForUser(CashTransferRequest cashTransferRequest) {
        createTransferTransactionAction.execute(cashTransferRequest);
    }

    public void createDepositTransactionForUser(CashTransferRequest cashTransferRequest) {
        createDepositTransactionAction.execute(cashTransferRequest);
    }

    public void createWithdrawTransactionForUser(CashTransferRequest cashTransferRequest) {
        createWithdrawTransactionAction.execute(cashTransferRequest);
    }
}
