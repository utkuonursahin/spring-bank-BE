package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.transaction.service.action.CreateTransferTransactionService;
import me.utku.springbank.user.User;
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
