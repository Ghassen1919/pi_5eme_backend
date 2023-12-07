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

public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInstrument")
    Integer idInstrument;
    String name;

    String symbole;

    Float quantite;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    String instrumentPhoto;
@ManyToOne
    Portefeuille portefeuille;
}