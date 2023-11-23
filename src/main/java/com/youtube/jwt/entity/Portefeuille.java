package com.youtube.jwt.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Portefeuille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPortefeuille")
    Integer idPortefeuille;

    float solde;

    @OneToOne(cascade= CascadeType.ALL)
    private User user;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Transaction> transaction;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Instrument> instrument;
}