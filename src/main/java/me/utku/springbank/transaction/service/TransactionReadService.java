package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.transaction.TransactionDto;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionReadService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionDto> getTransactionsBySender(UUID senderId) {
        return transactionRepository.findAllBySender_Id(senderId).stream().map(transactionMapper::toDto).toList();
    }

    public List<TransactionDto> getTransactionsBySenderOwner(UUID senderOwnerId) {
        return transactionRepository.findAllBySender_Owner_Id(senderOwnerId).stream().map(transactionMapper::toDto).toList();
    }
}
