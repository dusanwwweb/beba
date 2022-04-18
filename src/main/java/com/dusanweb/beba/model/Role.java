package com.dusanweb.beba.model;


import com.dusanweb.beba.enumeration.RoleType;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    //Constructor for tests
    public Role(RoleType name) {
        this.name = name;
    }
}