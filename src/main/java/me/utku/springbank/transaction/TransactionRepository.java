package me.utku.springbank.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllBySender_Id(UUID senderId);

    List<Transaction> findAllBySender_Owner_Id(UUID ownerId);
}
