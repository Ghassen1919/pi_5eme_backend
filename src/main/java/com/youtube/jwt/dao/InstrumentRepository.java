package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Instrument;

import com.youtube.jwt.entity.Portefeuille;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument,Integer> {

    Optional<Instrument> findBySymboleAndPortefeuille(String symbol, Portefeuille portefeuille);
}
