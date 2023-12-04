package com.youtube.jwt.service.Interfaces;

import com.youtube.jwt.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction add(Transaction a);
    Transaction edit(Transaction a);
    List<Transaction> selectAll();
    Transaction selectById(int id);


    void deleteById(int id);
}
