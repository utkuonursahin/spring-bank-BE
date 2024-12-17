package me.utku.springbank.service.accountstock.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.account.AccountDto;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.dto.stockprice.StockPriceDto;
import me.utku.springbank.exception.OperationDeniedException;
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

@Component
@RequiredArgsConstructor
@Transactional
public class BuyAccountStockAction {
    private final AccountStockMapper accountStockMapper;
    private final AccountStockRepository accountStockRepository;
    private final AccountQueryService accountQueryService;
    private final StockPriceQueryService stockPriceQueryService;
    private final AccountService accountService;

    public GenericResponse<AccountStockDto> execute(AccountStockDto accountStockDto) {
        AccountStock accountStock = accountStockRepository
                .findByAccount_IdAndStock_Id(accountStockDto.account().id(), accountStockDto.stock().id())
                .orElse(null);

        if (accountStock != null) {
            accountStock.setQuantity(accountStock.getQuantity().add(accountStockDto.quantity()));
        } else {
            accountStock = accountStockMapper.toEntity(accountStockDto);
        }

        AccountDto account = accountQueryService.getAccount(accountStockDto.account().id());
        StockPriceDto stockPrice = stockPriceQueryService.getStockPrice(accountStockDto.stock().id());
        if (account.cash().compareTo(accountStockDto.quantity().multiply(stockPrice.stockPrice())) < 0) {
            throw new OperationDeniedException("Insufficient balance");
        }
        accountService.decreaseCash(account.id(), accountStockDto.quantity().multiply(stockPrice.stockPrice()));
        accountStockRepository.save(accountStock);
        return GenericResponse.ok(HttpStatus.OK.value(), accountStockMapper.toDto(accountStock));
    }
}
