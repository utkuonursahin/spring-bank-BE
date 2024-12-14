package me.utku.springbank.controller;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.stock.StockQueryService;
import me.utku.springbank.service.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockQueryService stockQueryService;
    private final StockService stockService;

    @GetMapping
    public List<StockDto> getStocks() {
        return stockQueryService.getStocks();
    }

    @PostMapping
    public ResponseEntity<GenericResponse<StockDto>> createStock(StockDto stockDto) {
        return stockService.createStock(stockDto).toResponseEntity();
    }
}
