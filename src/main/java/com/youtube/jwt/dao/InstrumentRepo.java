package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepo extends JpaRepository<Instrument, Integer> {

}

