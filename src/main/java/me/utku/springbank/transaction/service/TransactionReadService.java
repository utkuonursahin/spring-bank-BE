package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.transaction.Transaction;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import me.utku.springbank.transaction.dto.TransactionPageDto;
import me.utku.springbank.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionReadService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionPageDto getTransactionsBySender(UUID senderId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Id(senderId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsSentByUserAccount(User user, AccountDto account, int page, int size) {
        if (!account.owner().id().equals(user.getId()))
            throw new AccessDeniedException("You are not allowed to view transactions for this account");
        Page<Transaction> transactions = transactionRepository.findAllBySender_Id(account.id(), PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }

    public TransactionPageDto getTransactionsBySenderOwner(UUID senderOwnerId, int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAllBySender_Owner_Id(senderOwnerId, PageRequest.of(page, size));
        return transactionMapper.toPageDto(transactions);
    }
}
