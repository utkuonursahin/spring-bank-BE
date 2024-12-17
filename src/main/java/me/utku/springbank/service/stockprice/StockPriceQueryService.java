package me.utku.springbank.service.stockprice;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stockprice.StockPriceDto;
import me.utku.springbank.mapper.StockPriceMapper;
import me.utku.springbank.repository.StockPriceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockPriceQueryService {
    private final StockPriceRepository stockPriceRepository;
    private final StockPriceMapper stockPriceMapper;

    public StockPriceDto getStockPrice(UUID stockId) {
        return stockPriceMapper.toDto(stockPriceRepository.findFirstByStock_IdOrderByCreatedAtDesc(stockId));
    }
}
