package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
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

    public GenericResponse<TransactionDto> createTransactionForUser(User user, TransactionDto transactionDto) {
        if (!transactionDto.sender().owner().id().equals(user.getId()))
            throw new AccessDeniedException("You are not allowed to create transaction for this account");
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDto));
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Transaction processed successfully", transactionMapper.toDto(transaction));
    }
}
