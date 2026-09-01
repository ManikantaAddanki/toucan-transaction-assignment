package com.example.transactionstarter.service;

import com.example.transactionstarter.dto.CreateTransactionRequest;
import com.example.transactionstarter.dto.UpdateStatusRequest;
import com.example.transactionstarter.exception.DuplicateTransactionException;
import com.example.transactionstarter.exception.TransactionNotFoundException;
import com.example.transactionstarter.model.Transaction;
import com.example.transactionstarter.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(CreateTransactionRequest request) {

        if (transactionRepository.existsById(request.getTransactionId())) {
            throw new DuplicateTransactionException(
                    "Transaction ID already exists");
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionId(request.getTransactionId());
        transaction.setCustomerId(request.getCustomerId());
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency().toUpperCase());
        transaction.setTransactionType(
                request.getTransactionType().toUpperCase());
        transaction.setTransactionStatus("PENDING");

        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String transactionId) {

        return transactionRepository.findById(transactionId)
                .orElseThrow(() ->
                        new TransactionNotFoundException(
                                "Transaction not found"));
    }

    public Transaction updateStatus(
            String transactionId,
            UpdateStatusRequest request) {

        Transaction transaction = getTransaction(transactionId);

        String currentStatus =
                transaction.getTransactionStatus();

        String newStatus =
                request.getStatus().toUpperCase();

        if (!currentStatus.equals("PENDING")) {
            throw new IllegalArgumentException(
                    "Only PENDING transactions can be updated");
        }

        if (!newStatus.equals("COMPLETED")
                && !newStatus.equals("FAILED")) {

            throw new IllegalArgumentException(
                    "Status must be COMPLETED or FAILED");
        }

        transaction.setTransactionStatus(newStatus);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getCustomerTransactions(
            String customerId) {

        return transactionRepository
                .findByCustomerId(customerId);
    }
}