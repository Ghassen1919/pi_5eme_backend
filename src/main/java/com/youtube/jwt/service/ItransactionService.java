package com.youtube.jwt.service;

import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.TypeClaim;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItransactionService {
    void Sell (Transaction transaction);
    List<Transaction> selectAll();
}
