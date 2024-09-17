package me.utku.springbank.transaction;

import me.utku.springbank.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findAllBySender_IdOrReceiver_Id(UUID senderAccountId, UUID receiverAccountId , Pageable pageable);

    Page<Transaction> findAllBySender_Owner_IdOrReceiver_Owner_Id(UUID ownerId, UUID receiverId, Pageable pageable);

    Page<Transaction> findAllBySender_OwnerOrReceiver_Owner(User senderOwner, User receiverOwner, Pageable pageable);

    Page<Transaction> findAllBySender_Owner(User user, Pageable pageable);

    Page<Transaction> findAllByReceiverOwner(User user, Pageable pageable);
}
