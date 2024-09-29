package me.utku.springbank.account.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.account.Account;
import me.utku.springbank.account.AccountMapper;
import me.utku.springbank.account.AccountRepository;
import me.utku.springbank.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public void transferMoneyBetweenAccounts(UUID senderId, UUID receiverId, BigDecimal amount) {
        Account sender = accountRepository.findById(senderId).orElseThrow(EntityNotFoundException::new);
        Account receiver = accountRepository.findById(receiverId).orElseThrow(EntityNotFoundException::new);

        sender.setCash(sender.getCash().subtract(amount));
        receiver.setCash(receiver.getCash().add(amount));

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
}
