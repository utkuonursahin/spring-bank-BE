package me.utku.springbank.repository;

import me.utku.springbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAllByOwner_Id(UUID userId);
}
