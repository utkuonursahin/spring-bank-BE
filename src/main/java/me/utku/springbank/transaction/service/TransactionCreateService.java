package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCreateService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public GenericResponse<TransactionDto> createTransactionForUser(User user, TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction.getSender().setOwner(user);
        transaction = transactionRepository.save(transaction);
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Transaction processed successfully", transactionMapper.toDto(transaction));
    }
}
