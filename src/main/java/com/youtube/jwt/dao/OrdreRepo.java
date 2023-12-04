package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Ordre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdreRepo extends JpaRepository<Ordre, Integer> {
    // You can add custom queries or methods here if needed
}

