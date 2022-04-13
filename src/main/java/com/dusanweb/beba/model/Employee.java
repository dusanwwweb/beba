package com.dusanweb.beba.model;

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
public class Employee extends User{

    /*
    JPA RELATIONSHIPS
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "employee_notebook",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "notebook_id")
    )
    private Set<Notebook> notebooks = new HashSet<>();


    //BIDIRECTIONAL
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "section_id", insertable = true, updatable = true, nullable=true)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

/*
    //UNIDIRECTIONAL
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Section section;

 */
}
