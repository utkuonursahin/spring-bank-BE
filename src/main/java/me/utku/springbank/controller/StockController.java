package me.utku.springbank.controller;

import me.utku.springbank.dto.stock.StockDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.stock.StockQueryService;
import me.utku.springbank.service.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockQueryService stockQueryService;
    private final StockService stockService;

    public StockController(StockQueryService stockQueryService, StockService stockService) {
        this.stockQueryService = stockQueryService;
        this.stockService = stockService;
    }

    @GetMapping("/public")
    @PreAuthorize("hasRole('USER')")
    public List<StockDto> getStocks() {
        return stockQueryService.getStocks();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse<StockDto>> createStock(StockDto stockDto) {
        return stockService.createStock(stockDto).toResponseEntity();
    }
}
