package me.utku.springbank.transaction.service;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.generic.CrudService;
import me.utku.springbank.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionCrudService extends CrudService<Transaction> {

    public TransactionCrudService(JpaRepository<Transaction, UUID> repository, BaseMapper<Transaction> mapper) {
        super(repository, mapper);
    }
}
