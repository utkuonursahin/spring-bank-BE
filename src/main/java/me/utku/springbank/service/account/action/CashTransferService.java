package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashTransferService {
    private final AccountRepository accountRepository;

    public void issueCashTransfer(UUID senderId, UUID receiverId, BigDecimal amount) {
        findUserAndSetCash(senderId, amount, false);
        findUserAndSetCash(receiverId, amount, true);
    }

    public void findUserAndSetCash(UUID userId, BigDecimal amount, boolean isReceiver) {
        Account account = accountRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (isReceiver) {
            account.setCash(account.getCash().add(amount));
        } else {
            account.setCash(account.getCash().subtract(amount));
        }
        accountRepository.save(account);
    }
}
