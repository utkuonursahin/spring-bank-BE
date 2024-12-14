package me.utku.springbank.service.stockprice;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stockprice.StockPriceDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.StockPriceMapper;
import me.utku.springbank.model.StockPrice;
import me.utku.springbank.repository.StockPriceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPriceService {
    private final StockPriceRepository stockPriceRepository;
    private final StockPriceMapper stockPriceMapper;

    public GenericResponse<StockPriceDto> createStockPrice(StockPriceDto stockPriceDto) {
        StockPrice stockPrice = stockPriceMapper.toEntity(stockPriceDto);
        stockPrice = stockPriceRepository.save(stockPrice);
        return GenericResponse.ok(HttpStatus.CREATED.value(), stockPriceMapper.toDto(stockPrice));
    }
}
