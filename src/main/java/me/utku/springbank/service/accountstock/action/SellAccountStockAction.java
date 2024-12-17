package me.utku.springbank.service.accountstock.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.enums.account.AccountType;
import me.utku.springbank.exception.EntityNotFoundException;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
import me.utku.springbank.service.account.AccountQueryService;
import me.utku.springbank.service.account.AccountService;
import me.utku.springbank.service.stockprice.StockPriceQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional
public class SellAccountStockAction {
    private final AccountStockRepository accountStockRepository;
    private final AccountStockMapper accountStockMapper;
    private final AccountService accountService;
    private final AccountQueryService accountQueryService;
    private final StockPriceQueryService stockPriceQueryService;

    public GenericResponse<AccountStockDto> execute(UUID userId, AccountStockDto accountStockDto) {
        AccountDto account = accountQueryService.getUserAccountByType(userId, AccountType.SAVINGS);
        AccountStock accountStock = accountStockRepository.findByAccount_IdAndStock_Id(account.id(), accountStockDto.stock().id()).orElseThrow(EntityNotFoundException::new);
        BigDecimal stockValue = stockPriceQueryService.getStockPrice(accountStock.getStock().getId()).stockPrice().multiply(accountStock.getQuantity());
        accountService.increaseCash(accountStock.getAccount().getId(), stockValue);

        if (accountStock.getQuantity().equals(accountStockDto.quantity())) {
            accountStockRepository.delete(accountStock);
        } else {
            accountStock.setQuantity(accountStock.getQuantity().subtract(accountStockDto.quantity()));
            accountStockRepository.save(accountStock);
        }

        return GenericResponse.ok(HttpStatus.OK.value(), accountStockMapper.toDto(accountStock));
    }
}
