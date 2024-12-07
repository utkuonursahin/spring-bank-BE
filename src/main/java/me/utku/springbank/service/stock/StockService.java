package me.utku.springbank.service.stock;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
}
