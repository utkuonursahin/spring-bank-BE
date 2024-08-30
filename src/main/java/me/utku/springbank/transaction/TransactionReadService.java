package me.utku.springbank.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionReadService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionDto> getTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::transactionToTransactionDto).toList();
    }

    public TransactionDto getTransaction(UUID id) {
        return transactionRepository.findById(id).map(transactionMapper::transactionToTransactionDto).orElse(null);
    }
}
