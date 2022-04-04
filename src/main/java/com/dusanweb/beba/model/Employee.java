package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.EmployeeRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("employee")
public class
Employee extends User{

    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;


    /*
        JPA RELATIONSHIPS
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "employee_notebook",
            joinColumns = @JoinColumn(name = "idEmployee"), //TODO inheritance ???
            inverseJoinColumns = @JoinColumn(name = "idNotebook")
    )
    private Set<Notebook> notebooks = new HashSet<>();

    /*
    //BIDIRECTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;
     */

    //UNIDIRECTIONAL
    // the best way to model a one-to-many relationship is to use just
    // @ManyToOne annotation on the child entity.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Section section;
}
