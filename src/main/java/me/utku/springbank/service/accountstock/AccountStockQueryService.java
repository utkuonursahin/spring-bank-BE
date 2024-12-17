package me.utku.springbank.service.accountstock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
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

    public List<AccountStockDto> getAccountStocks(UUID accountId) {
        List<AccountStock> accountStocks = accountStockRepository.findAllByAccount_Id(accountId);
        return accountStocks.stream()
                .map(accStock -> {
                    accStock.setValue(stockPriceQueryService.getStockPrice(accStock.getStock().getId()).stockPrice().multiply(accStock.getQuantity()));
                    return accStock;
                })
                .map(accountStockMapper::toDto).toList();
    }

    public AccountStockDto getAccountStock(UUID accountId, UUID stockId) {
        AccountStock accountStock = accountStockRepository.findByAccount_IdAndStock_Id(accountId, stockId).orElseThrow();
        accountStock.setValue(stockPriceQueryService.getStockPrice(accountStock.getStock().getId()).stockPrice().multiply(accountStock.getQuantity()));
        return accountStockMapper.toDto(accountStock);
    }
}
