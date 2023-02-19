package com.utku.repositories;

import com.utku.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE t.senderID = :id or t.receiverID = :id")
    List<Transaction> findAllBySenderID(@Param("id") Integer senderID);
}
