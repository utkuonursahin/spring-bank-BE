package me.utku.springbank.account.service.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.Account;
import me.utku.springbank.account.AccountRepository;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferCashService {
    private final AccountRepository accountRepository;

    public void transferMoneyBetweenAccounts(UUID senderId, UUID receiverId, BigDecimal amount) {
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
