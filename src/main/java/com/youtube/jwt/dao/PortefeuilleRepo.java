package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Portefeuille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortefeuilleRepo extends JpaRepository<Portefeuille, Integer> {
    // You can add custom queries or methods here if needed
}

