package me.utku.springbank.transaction.service.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.AccountDto;
import me.utku.springbank.account.service.AccountQueryService;
import me.utku.springbank.account.service.AccountService;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.transaction.TransactionMapper;
import me.utku.springbank.transaction.TransactionRepository;
import me.utku.springbank.transaction.dto.TransactionDto;
import me.utku.springbank.user.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransferTransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountQueryService accountQueryService;
    private final AccountService accountService;

    public TransactionDto createTransferTransactionForUser(User user, TransactionDto transactionRequest) {
        if (!checkSenderEligibility(user, transactionRequest)) {
            throw new OperationDeniedException("You are not allowed to perform this operation");
        }
        accountService.transferMoneyBetweenAccounts(transactionRequest.sender().id(), transactionRequest.receiver().id(), transactionRequest.amount());
        return transactionMapper.toDto(transactionRepository.save(transactionMapper.toEntity(transactionRequest)));
    }

    public boolean checkSenderEligibility(User user, TransactionDto transactionRequest) {
        AccountDto senderAccount = accountQueryService.getAccount(transactionRequest.sender().id());
        return senderAccount.owner().id().equals(user.getId()) && !transactionRequest.receiver().id().equals(transactionRequest.sender().id());
    }
}
