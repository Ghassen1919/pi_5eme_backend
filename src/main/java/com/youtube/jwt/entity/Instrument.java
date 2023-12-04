package com.youtube.jwt.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

<<<<<<< Updated upstream
import javax.persistence.*;
=======
import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;

>>>>>>> Stashed changes

@Entity
@Getter
@Setter
<<<<<<< Updated upstream
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Instrument {
=======
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Instrument implements Serializable {
>>>>>>> Stashed changes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInstrument")
    Integer idInstrument;
    String name;

<<<<<<< Updated upstream
    String symbole;

    Float quantite;

@ManyToOne
    Portefeuille portefeuille;
}
=======
    String Symbole;



}
>>>>>>> Stashed changes
