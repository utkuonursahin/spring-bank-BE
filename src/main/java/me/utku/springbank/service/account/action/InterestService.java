package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService implements ScheduledTask {
    private final AccountRepository accountRepository;

    @Value("${spring.project.bank.interest_rate}")
    private float interestRate;

    //@Scheduled(fixedDelay = 1000 * 60)
    public void perform() {
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            BigDecimal cash = account.getCash();
            account.setCash(cash.add(cash.multiply(BigDecimal.valueOf(interestRate))));
        });
        accountRepository.saveAll(accounts);
    }
}
