package me.utku.springbank.service.accountstock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountStockQueryService {
    private final AccountStockRepository accountStockRepository;
    private final AccountStockMapper accountStockMapper;

    public List<AccountStockDto> getAccountStocks(UUID accountId) {
        List<AccountStock> accountStocks = accountStockRepository.findAllByAccount_Id(accountId);
        return accountStocks.stream().map(accountStockMapper::toDto).toList();
    }

    public AccountStockDto getAccountStock(UUID accountId, UUID stockId) {
        AccountStock accountStock = accountStockRepository.findByAccount_IdAndStock_Id(accountId, stockId).orElseThrow();
        return accountStockMapper.toDto(accountStock);
    }
}
