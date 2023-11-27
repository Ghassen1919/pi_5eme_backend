package com.youtube.jwt.service;

import com.youtube.jwt.entity.Portefeuille;

import java.util.List;
import java.util.Optional;

public interface IPortefeuilleService {
    public Portefeuille add(Portefeuille portefeuille) ;



    public Portefeuille edit(Portefeuille portefeuille) ;


    public List<Portefeuille> selectAll() ;


    public Portefeuille selectById(int id) ;


    public void deleteById(int id) ;
}
