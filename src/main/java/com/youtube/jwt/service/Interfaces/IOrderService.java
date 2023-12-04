package com.youtube.jwt.service.Interfaces;

import com.youtube.jwt.entity.Ordre;
import com.youtube.jwt.entity.Transaction;

import java.util.List;

public interface IOrderService {

    Ordre add(Ordre a);
    Ordre edit(Ordre a);
    List<Ordre> selectAll();
    Ordre selectById(int id);


    void deleteById(int id);
}
