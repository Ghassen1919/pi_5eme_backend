package com.youtube.jwt.service.Interfaces;

import com.youtube.jwt.entity.Instrument;
import com.youtube.jwt.entity.Ordre;

import java.util.List;

public interface IInstrumentService {
    Instrument add(Instrument a);
    Instrument edit(Instrument a);
    List<Instrument> selectAll();
    Instrument selectBySymbol(int id);


    void deleteById(int id);
}
