package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Transaction;
<<<<<<< Updated upstream
import com.youtube.jwt.service.TransactionService;
=======
import com.youtube.jwt.service.Interfaces.ITransactionService;
import com.youtube.jwt.service.TransactionService;
import lombok.AllArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< Updated upstream

@RestController
@RequestMapping("/transactions")
public class TransactionController {
=======
@RestController
@AllArgsConstructor

@RequestMapping("/transactions")
public class TransactionController {
    private ITransactionService iTransaction;
>>>>>>> Stashed changes
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
<<<<<<< Updated upstream
        return transactionService.getAllTransactions();
=======
        return transactionService.selectAll();
>>>>>>> Stashed changes
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
<<<<<<< Updated upstream
        return transactionService.getTransactionById(id);
=======
        return transactionService.selectById(id);
>>>>>>> Stashed changes
    }

    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
<<<<<<< Updated upstream
        return transactionService.saveTransaction(transaction);
=======
        return transactionService.add(transaction);
>>>>>>> Stashed changes
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Integer id) {
<<<<<<< Updated upstream
        transactionService.deleteTransaction(id);
    }
}
=======
        transactionService.deleteById(id);
    }
}

>>>>>>> Stashed changes
