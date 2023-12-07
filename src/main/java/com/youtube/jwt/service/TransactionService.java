package com.youtube.jwt.service;

<<<<<<< Updated upstream
import com.youtube.jwt.dao.TransactionRepository;
import com.youtube.jwt.dao.TransatioRepo;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ItransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }
}
=======
import com.youtube.jwt.dao.TransactionRepo;
import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.service.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepo transactionRepository;


    @Override
    public Transaction add(Transaction transaction) {
        // You can add additional business logic/validation here before saving to the repository
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction edit(Transaction transaction) {
        // Check if the transaction with the given ID exists before updating
        Optional<Transaction> existingTransaction = transactionRepository.findById(transaction.getIdTransaction());

        if (existingTransaction.isPresent()) {
            // Update the existing transaction with the new values
            Transaction updatedTransaction = transactionRepository.save(transaction);
            return updatedTransaction;
        } else {
            // Handle the case where the transaction with the given ID is not found
            // You might want to throw an exception or handle it according to your requirements
            return null;
        }
    }

    @Override
    public List<Transaction> selectAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction selectById(int id) {
        // You can add additional error handling or business logic as needed
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        // You can add additional error handling or business logic as needed
        transactionRepository.deleteById(id);
    }
}

>>>>>>> Stashed changes
