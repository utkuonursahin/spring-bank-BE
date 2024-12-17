package me.utku.springbank.service.transaction.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.enums.transaction.TransactionType;
import me.utku.springbank.mapper.TransactionMapper;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransferTransactionAction {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public void execute(CashTransferRequest cashTransferRequest) {
        Transaction transaction = transactionMapper.toEntity(cashTransferRequest);
        transaction.setType(TransactionType.TRANSFER);
        transactionMapper.toDto(transactionRepository.save(transaction));
    }
}
