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
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClaim")
    Integer idClaim;
    @Temporal (TemporalType.DATE)
    Date dateClaim;
    @Temporal (TemporalType.DATE)
    Date dateTraite;

    Boolean TraiteClaim;
    @Enumerated(EnumType.STRING)
    TypeClaim type;
    String descClaim;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    String AttachClaim;

     private String RefTR;
    String refclaim ;

    @ManyToOne(cascade=CascadeType.ALL)
    private User userc;


}
