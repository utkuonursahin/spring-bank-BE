package me.utku.springbank.service.stockprice;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.model.StockPrice;
import me.utku.springbank.repository.StockPriceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockPriceQueryService {
    private final StockPriceRepository stockPriceRepository;

    public StockPrice getStockPrice(UUID stockId) {
        return stockPriceRepository.findFirstByStock_IdOrderByCreatedAtDesc(stockId);
    }
}
