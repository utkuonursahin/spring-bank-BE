package me.utku.springbank.service.accountstock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
import me.utku.springbank.service.account.AccountQueryService;
import me.utku.springbank.service.stockprice.StockPriceQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountStockQueryService {
    private final AccountStockRepository accountStockRepository;
    private final AccountStockMapper accountStockMapper;
    private final StockPriceQueryService stockPriceQueryService;
    private final AccountQueryService accountQueryService;

    public List<AccountStockDto> getAccountStocks(UUID userId) {
        AccountDto account = accountQueryService.getUserAccountByType(userId, AccountType.SAVINGS);
        List<AccountStock> accountStocks = accountStockRepository.findAllByAccount_Id(account.id());
        return accountStocks.stream()
                .map(accStock -> {
                    accStock.setValue(stockPriceQueryService.getStockPrice(accStock.getStock().getId()).stockPrice().multiply(accStock.getQuantity()));
                    return accStock;
                })
                .map(accountStockMapper::toDto).toList();
    }

    public AccountStockDto getAccountStock(UUID userId, UUID stockId) {
        AccountDto account = accountQueryService.getUserAccountByType(userId, AccountType.SAVINGS);
        AccountStock accountStock = accountStockRepository.findByAccount_IdAndStock_Id(account.id(), stockId).orElseThrow();
        accountStock.setValue(stockPriceQueryService.getStockPrice(accountStock.getStock().getId()).stockPrice().multiply(accountStock.getQuantity()));
        return accountStockMapper.toDto(accountStock);
    }
}
