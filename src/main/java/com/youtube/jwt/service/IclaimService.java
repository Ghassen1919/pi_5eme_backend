package com.youtube.jwt.service;

import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.TypeClaim;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IclaimService {
    void modifier(Integer id,boolean traite);
    void ajouter (MultipartFile file, String desc, TypeClaim type,String t);
    List<Claim> selectAll();

    List<Claim> search(String q);
    List<Claim> search2(Date q);
   String getForbiddenWords();

}