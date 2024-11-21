package me.utku.springbank.service.transaction;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.dto.transaction.TransactionDto;
import me.utku.springbank.service.transaction.action.CreateTransferTransactionService;
import me.utku.springbank.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CreateTransferTransactionService createTransferTransactionService;

    public GenericResponse<TransactionDto> createTransferTransactionForUser(User user, TransactionDto transactionRequest) {
        TransactionDto createdTransaction = createTransferTransactionService.createTransferTransactionForUser(user, transactionRequest);
        return GenericResponse.ok(HttpStatus.CREATED.value(), createdTransaction);
    }
}
