package com.youtube.jwt.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ordre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrdre")
    Integer idOrdre;

    TypeOrdre typeOrdre;

    float Quantite ;

    float prixLimite;

    @OneToOne
    User user;
    @OneToOne
    Instrument instrument;



}
