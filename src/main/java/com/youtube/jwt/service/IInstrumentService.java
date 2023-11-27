package com.youtube.jwt.service;

import com.youtube.jwt.entity.Instrument;

import java.util.List;
import java.util.Optional;

public interface IInstrumentService {
    public Instrument add(Instrument instrument) ;



    public Instrument edit(Instrument instrument) ;


    public List<Instrument> selectAll() ;


    public Instrument selectById(int id) ;



    public void deleteById(int id) ;

}
