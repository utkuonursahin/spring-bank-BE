package me.utku.springbank.controller;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.stockprice.StockPriceDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.service.stockprice.StockPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/stock-price")
@RequiredArgsConstructor
public class StockPriceController {
    private final StockPriceService stockPriceService;

    @PostMapping
    public ResponseEntity<GenericResponse<StockPriceDto>> createStockPrice(@RequestBody StockPriceDto stockPriceDto) {
        return stockPriceService.createStockPrice(stockPriceDto).toResponseEntity();
    }
}
