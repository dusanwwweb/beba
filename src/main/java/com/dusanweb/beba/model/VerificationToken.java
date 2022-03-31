package com.dusanweb.beba.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

    /*
        Whenever a user is registered there is a generation of a token
        which is stored to the database through this entity
        and send this token as a part of the activation link to the user.
        Whenever user clicks on this link we look up the user associated
        to this token and enable that user
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idToken;

    private String token;

    @OneToOne(fetch = LAZY)
    private User user;

    private Instant expiryDate;
}