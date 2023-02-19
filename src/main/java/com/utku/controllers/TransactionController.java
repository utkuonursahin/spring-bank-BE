package com.utku.controllers;

import com.utku.models.Transaction;
import com.utku.models.User;
import com.utku.records.NewTransactionRequest;
import com.utku.repositories.TransactionRepository;
import com.utku.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    //GET /api/v1/transaction - get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    //GET /api/v1/transaction/{id} - get a transaction by id
    @GetMapping("/{id}")
    public Optional<Transaction> getTransaction(@PathVariable("id") String id) {
        return transactionRepository.findById(id);
    }

    //GET /api/v1/transaction/sender/{id} - get all transactions by sender id
    @GetMapping("/sender/{id}")
    public List<Transaction> getTransactionsBySenderID(@PathVariable("id") Integer id) {
        return transactionRepository.findAllBySenderID(id);
    }

    //POST /api/v1/transaction - create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody NewTransactionRequest req) {
        //Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionID(transaction.generateTransactionID());
        transaction.setType(req.type() == null ? " " : req.type());
        transaction.setSenderID(req.senderID() == null ? 0 : req.senderID());
        transaction.setReceiverID(req.receiverID() == null ? 0 : req.receiverID()   );
        transaction.setAmount(req.amount() == null ? 0 : req.amount());

        //Find the sender and receiver
        User sender = userRepository.findById(transaction.getSenderID()).orElse(null);
        User receiver = userRepository.findById(transaction.getReceiverID()).orElse(null);
        //Be sure that sender and receiver are exist
        if(sender == null || receiver == null){
            return null;
        }

        //Be sure that the transaction is valid
        if(!transaction.isTransactionValid(sender)){
            return null;
        }

        //According to the transaction type, update the sender and receiver balances
        switch (transaction.getType().toLowerCase()) {
            case "transfer":
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                receiver.setBalance(receiver.getBalance() + transaction.getAmount());
                break;
            case "deposit":
                sender.setBalance(sender.getBalance() + transaction.getAmount());
                break;
            case "withdraw":
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                break;
            case "debt":
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                sender.setDebt(sender.getDebt() + transaction.getAmount());
                break;
        }


        //Save the sender and receiver changes to database
        userRepository.save(sender);
        userRepository.save(receiver);

        //Save the transaction to database and return it
        return transactionRepository.save(transaction);
    }

    //DELETE /api/v1/transaction/{id} - delete a transaction by id
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") String id) {
        transactionRepository.deleteById(id);
    }
}