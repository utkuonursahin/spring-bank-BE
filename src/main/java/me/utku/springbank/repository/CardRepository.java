package me.utku.springbank.repository;

import me.utku.springbank.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByOwner_Id(UUID userId);

    Optional<Card> findByCardNumber(String cardNumber);
}
