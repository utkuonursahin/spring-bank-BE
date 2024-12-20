package me.utku.springbank.service.stock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.StockMapper;
import me.utku.springbank.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public GenericResponse<StockDto> createStock(StockDto stockDto) {
        return GenericResponse.ok(HttpStatus.CREATED.value(), stockMapper.toDto(stockRepository.save(stockMapper.toEntity(stockDto))));
    }
}
