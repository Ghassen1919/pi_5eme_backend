package com.youtube.jwt.service;

import com.youtube.jwt.dao.TransatioRepo;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.TypeTransaction;
import com.youtube.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionService implements ItransactionService {
    private TransatioRepo transationRepo;

    private UserDao userDao;
    @Override
    public void Sell(Transaction transaction) {
        transaction.setType(TypeTransaction.Sell);
        Calendar cal = Calendar.getInstance();
        Date dateclaim = cal.getTime();
        transaction.setDateTransaction(dateclaim);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            User user = userDao.findByUserName(currentUserName);
            transaction.setUserc(user);
            transationRepo.save(transaction);

    }
    @Override
    public List<Transaction> selectAll() {
        return transationRepo.findAll();
    }
}
