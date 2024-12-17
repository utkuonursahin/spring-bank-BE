package me.utku.springbank.service.transaction;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.transaction.TransactionPageDto;
import me.utku.springbank.mapper.TransactionMapper;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionQueryService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionPageDto getAccountTransactions(UUID accountId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_IdOrReceiver_Id(accountId, accountId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getAccountOwnerTransactions(UUID ownerId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner_IdOrReceiver_Owner_IdOrderByCreatedAtDesc(ownerId, ownerId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getUserTransactions(User user, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner_IdOrReceiver_Owner_IdOrderByCreatedAtDesc(user.getId(), user.getId(), PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsSentByUser(User user, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner_Id(user.getId(), PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsReceivedByUser(User user, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllByReceiverOwner_Id(user.getId(), PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getUserAccountTransactions(User user, UUID accountId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_IdOrReceiver_IdAndSender_Owner_IdOrReceiver_Owner_IdOrderByCreatedAtDesc(accountId, accountId, user.getId(), user.getId(), PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }
}
