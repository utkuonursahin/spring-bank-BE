package me.utku.springbank.service.accountstock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.AccountStockMapper;
import me.utku.springbank.model.AccountStock;
import me.utku.springbank.repository.AccountStockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountStockService {
    private final AccountStockRepository accountStockRepository;
    private final AccountStockMapper accountStockMapper;

    public GenericResponse<AccountStockDto> buyAccountStock(AccountStockDto accountStockDto) {
        AccountStock accountStock = accountStockMapper.toEntity(accountStockDto);
        accountStockRepository.save(accountStock);
        return GenericResponse.ok(HttpStatus.OK.value(), accountStockMapper.toDto(accountStock));
    }
}
