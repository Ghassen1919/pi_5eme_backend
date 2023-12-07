package com.youtube.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
<<<<<<< Updated upstream
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Portefeuille {
=======
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Portefeuille implements Serializable {
>>>>>>> Stashed changes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPortefeuille")
    Integer idPortefeuille;

    float solde;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Transaction> transaction;
    @OneToOne(mappedBy = "portefeuille")
    private User user;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Instrument> instrument;
}
=======
    @OneToOne(cascade=CascadeType.ALL)
=======
    @OneToOne(mappedBy = "portefeuille")
    @JsonIgnore
>>>>>>> Stashed changes
    private User user;


    @OneToMany(cascade=CascadeType.ALL)
    private Set<Transaction> transaction;

    @OneToMany(cascade=CascadeType.ALL)
<<<<<<< Updated upstream
    private  Set<Instrument> instrument;
}

>>>>>>> Stashed changes
=======
    private Set<Instrument> instrument;
}




>>>>>>> Stashed changes
