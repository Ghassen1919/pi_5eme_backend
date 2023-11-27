package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Portefeuille;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortefeuilleRepository extends JpaRepository<Portefeuille,Integer> {
    Optional<Portefeuille> findByUserUserName(String username);
}
