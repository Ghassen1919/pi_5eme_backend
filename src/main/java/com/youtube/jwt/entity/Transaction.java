package com.youtube.jwt.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Transaction implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTransaction")
    Integer idTransaction;
    @Temporal(TemporalType.DATE)
    Date dateTransaction;
    Float montant;
    Float quantite;
    String RefTransaction;



    @ManyToOne(cascade=CascadeType.ALL)
    private User userc;


}
