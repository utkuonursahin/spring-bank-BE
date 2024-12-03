package me.utku.springbank.service.transaction;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.dto.transaction.TransactionDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.transaction.action.CreateTransferTransactionAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CreateTransferTransactionAction createTransferTransactionAction;

    public GenericResponse<TransactionDto> createTransferTransactionForUser(CashTransferRequest cashTransferRequest) {
        TransactionDto createdTransaction = createTransferTransactionAction.execute(cashTransferRequest);
        return GenericResponse.ok(HttpStatus.CREATED.value(), createdTransaction);
    }
}
