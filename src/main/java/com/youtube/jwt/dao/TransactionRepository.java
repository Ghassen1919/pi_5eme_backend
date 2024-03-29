package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByBuyerOrSeller(String buyer,String seller);
}
