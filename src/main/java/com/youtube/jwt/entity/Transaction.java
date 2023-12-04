package com.youtube.jwt.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    String RefTransaction;

    String buyer;
    String seller;



<<<<<<< Updated upstream
=======
    @OneToMany(cascade=CascadeType.ALL)
    private List<User> userc;
>>>>>>> Stashed changes


}

