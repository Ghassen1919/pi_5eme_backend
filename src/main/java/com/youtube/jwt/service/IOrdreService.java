package com.youtube.jwt.service;

import com.youtube.jwt.entity.Ordre;

import java.util.List;
import java.util.Optional;

public interface IOrdreService {


    public Ordre add(Ordre ordre) ;



    public Ordre edit(Ordre ordre) ;



    public List<Ordre> selectAll() ;


    public Ordre selectById(int id) ;


    public void deleteById(int id) ;
    public List<Ordre> selectbuy();
    public void sellInstrument(float quantite,float prix,String symbol);

    void buyInstrument(Ordre ordre);
}
