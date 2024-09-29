package me.utku.springbank.transaction.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.service.AccountReadService;
import me.utku.springbank.account.service.AccountUpdateService;
import me.utku.springbank.exception.OperationDeniedException;
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
    private final AccountReadService accountReadService;
    private final AccountUpdateService accountUpdateService;

    public GenericResponse<TransactionDto> createTransferTransactionForUser(User user, TransactionDto transactionRequest) {
        AccountDto senderAccount = accountReadService.getAccount(transactionRequest.sender().id());

        if (!senderAccount.owner().id().equals(user.getId()) || transactionRequest.receiver().id().equals(transactionRequest.sender().id())) {
            throw new OperationDeniedException("You are not allowed to perform this operation");
        }

        if (senderAccount.cash().compareTo(transactionRequest.amount()) < 0) {
            throw new OperationDeniedException("You do not have enough balance to perform this operation");
        }

        accountUpdateService.transferMoneyBetweenAccounts(transactionRequest.sender().id(), transactionRequest.receiver().id(), transactionRequest.amount());

        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionRequest));
        return new GenericResponse<>(HttpStatus.CREATED.value(), "Transaction processed successfully", transactionMapper.toDto(transaction));
    }
}
