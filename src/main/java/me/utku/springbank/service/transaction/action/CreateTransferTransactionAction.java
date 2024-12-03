package me.utku.springbank.service.transaction.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.account.CashTransferRequest;
import me.utku.springbank.dto.transaction.TransactionDto;
import me.utku.springbank.enums.transaction.TransactionType;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.mapper.TransactionMapper;
import me.utku.springbank.model.Transaction;
import me.utku.springbank.repository.TransactionRepository;
import me.utku.springbank.service.account.AccountQueryService;
import me.utku.springbank.service.auth.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransferTransactionAction {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AuthService authService;
    private final AccountQueryService accountQueryService;

    public TransactionDto execute(CashTransferRequest cashTransferRequest) {
        if (!checkSenderEligibility(cashTransferRequest)) {
            throw new OperationDeniedException("You are not allowed to perform this operation");
        }
        Transaction transaction = transactionMapper.toEntity(cashTransferRequest);
        transaction.setType(TransactionType.TRANSFER);
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    public boolean checkSenderEligibility(CashTransferRequest cashTransferRequest) {
        AccountDto senderAccount = accountQueryService.getAccount(cashTransferRequest.sender().id());
        return authService.getAuthenticatedUser().getId().equals(senderAccount.owner().id()) && senderAccount.cash().compareTo(cashTransferRequest.amount()) >= 0;
    }
}
