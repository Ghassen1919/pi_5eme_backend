package com.youtube.jwt.service.Interfaces;

import com.youtube.jwt.entity.Ordre;
import com.youtube.jwt.entity.Portefeuille;

import java.util.List;

public interface IPortefeuilleService {
    Portefeuille add(Portefeuille a);
    Portefeuille edit(Portefeuille a);
    List<Portefeuille> selectAll();
    Portefeuille selectById(int id);


    void deleteById(int id);
}
