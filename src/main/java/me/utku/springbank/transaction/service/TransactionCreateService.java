package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.service.AccountReadService;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCreateService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountReadService accountReadService;

    public GenericResponse<TransactionDto> createTransactionForUser(User user, TransactionDto transactionRequest) {
        AccountDto senderAccount = accountReadService.getAccount(transactionRequest.sender().id());
        if (!senderAccount.owner().id().equals(user.getId()) || transactionRequest.receiver().id().equals(transactionRequest.sender().id()))
            throw new AccessDeniedException("You are not allowed to perform this operation");

        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        transaction = transactionRepository.save(transaction);
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Transaction processed successfully", transactionMapper.toDto(transaction));
    }
}
