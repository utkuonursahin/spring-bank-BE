package me.utku.springbank.service.accountstock.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyAccountStockAction {
    private final AccountStockMapper accountStockMapper;
    private final AccountStockRepository accountStockRepository;

    public GenericResponse<AccountStockDto> execute(AccountStockDto accountStockDto) {
        AccountStock accountStock = accountStockMapper.toEntity(accountStockDto);
        accountStockRepository.save(accountStock);
        return GenericResponse.ok(HttpStatus.OK.value(), accountStockMapper.toDto(accountStock));
    }
}
