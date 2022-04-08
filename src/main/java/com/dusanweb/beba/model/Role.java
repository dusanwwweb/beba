package com.dusanweb.beba.model;


import com.dusanweb.beba.enumeration.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /*
    JPA RELATIONSHIPS
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    */

    /*
     User class has a Set of Roles but the Role class doesn’t have any references of User.
     And by default, no cascade operations on a @ManyToMany relationship – that means updating
     a User object won’t change the associated Role objects.
     */
}