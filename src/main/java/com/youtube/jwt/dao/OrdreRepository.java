package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Ordre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdreRepository extends JpaRepository<Ordre,Integer> {


}
