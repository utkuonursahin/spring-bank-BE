package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import me.utku.springbank.transaction.dto.TransactionPageDto;
import me.utku.springbank.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionReadService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionPageDto getTransactionsByAccount(UUID accountId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_IdOrReceiver_Id(accountId, accountId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsByAccountOwner(UUID ownerId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner_IdOrReceiver_Owner_Id(ownerId, ownerId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getUserTransactions(User senderOwner, User receiverOwner, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_OwnerOrReceiver_Owner(senderOwner, receiverOwner, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsSentByUser(User user, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner(user, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsReceivedByUser(User user, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllByReceiverOwner(user, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }
}
