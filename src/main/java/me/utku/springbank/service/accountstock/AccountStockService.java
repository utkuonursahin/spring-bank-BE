package me.utku.springbank.service.accountstock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.accountstock.AccountStockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.accountstock.action.BuyAccountStockAction;
import me.utku.springbank.service.accountstock.action.SellAccountStockAction;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountStockService {
    private final BuyAccountStockAction buyAccountStockAction;
    private final SellAccountStockAction sellAccountStockAction;

    public GenericResponse<AccountStockDto> buyAccountStock(UUID userId, AccountStockDto accountStockDto) {
        return buyAccountStockAction.execute(userId, accountStockDto);
    }

    public GenericResponse<AccountStockDto> sellAccountStock(UUID userId, AccountStockDto accountStockDto) {
        return sellAccountStockAction.execute(userId, accountStockDto);
    }
}
