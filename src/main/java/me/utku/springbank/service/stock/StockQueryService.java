package me.utku.springbank.service.stock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.mapper.StockMapper;
import me.utku.springbank.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockQueryService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public List<StockDto> getStocks() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toDto)
                .toList();
    }
}
