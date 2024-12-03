package me.utku.springbank.service.account.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.model.Account;
import me.utku.springbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedDepositInterestAction {
    private final AccountRepository accountRepository;
    private final static int ONE_DAY_TIMESTAMP = 1000 * 60 * 60 * 24;
    @Value("${spring.project.bank.daily_interest_rate}")
    private float interestRate;

    @Scheduled(fixedDelay = ONE_DAY_TIMESTAMP)
    public void execute() {
        List<Account> accounts = accountRepository.findAllByAccountType(AccountType.FIXED_DEPOSIT);
        accounts.forEach(account -> {
            BigDecimal cash = account.getCash();
            account.setCash(cash.add(cash.multiply(BigDecimal.valueOf(interestRate))));
        });
        accountRepository.saveAll(accounts);
    }
}
