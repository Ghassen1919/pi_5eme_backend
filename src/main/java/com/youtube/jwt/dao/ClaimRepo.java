package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
@Repository
public interface ClaimRepo extends JpaRepository<Claim,Integer>{
    List<Claim> findByRefclaim(String type);
    List<Claim> findByDateClaim(Date date);
    List<Claim> findByUserc(User user);
}
