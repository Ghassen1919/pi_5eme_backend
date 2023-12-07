package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Ordre;

import com.youtube.jwt.entity.TypeOrdre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdreRepository extends JpaRepository<Ordre,Integer> {

    List<Ordre> findAllByTypeOrdre(TypeOrdre typeOrdre);
    List<Ordre> findAllByTypeOrdreAndUserUserNameNot(TypeOrdre typeOrdre, String UserName);

}