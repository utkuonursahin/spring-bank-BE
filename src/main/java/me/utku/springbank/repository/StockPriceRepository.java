package me.utku.springbank.repository;

import me.utku.springbank.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockPriceRepository extends JpaRepository<StockPrice, UUID> {
    StockPrice findFirstByStock_IdOrderByCreatedAtDesc(UUID stockId);
}
