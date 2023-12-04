package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

<<<<<<< Updated upstream:src/main/java/com/youtube/jwt/dao/TransatioRepo.java
public interface TransatioRepo {

=======
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
>>>>>>> Stashed changes:src/main/java/com/youtube/jwt/dao/TransactionRepo.java
    List<Transaction> findByUserc(User user);
}
