package com.dusanweb.beba.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("parent")
public class Parent extends User{

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    /*
        JPA RELATIONSHIPS
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "child_parent",
            joinColumns = @JoinColumn(name = "idParent"), //TODO inheritance ???
            inverseJoinColumns = @JoinColumn(name = "idChild")
    )
    private Set<Child> children = new HashSet<>();

}
