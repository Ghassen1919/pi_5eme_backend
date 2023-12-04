package com.youtube.jwt.entity;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
<<<<<<< Updated upstream


=======
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
public class Ordre {
=======
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Ordre implements Serializable {
>>>>>>> Stashed changes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrdre")
    Integer idOrdre;

    TypeOrdre typeOrdre;

    float Quantite ;

    float prixLimite;

<<<<<<< Updated upstream
    @OneToOne
    User user;
    @OneToOne
    Instrument instrument;

=======
>>>>>>> Stashed changes


}
