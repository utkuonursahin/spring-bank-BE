package me.utku.springbank.repository;

import me.utku.springbank.model.AccountStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, UUID> {
    List<AccountStock> findAllByAccount_Id(UUID accountId);

    Optional<AccountStock> findByAccount_IdAndStock_Id(UUID accountId, UUID stockId);
}
