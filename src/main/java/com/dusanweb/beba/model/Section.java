package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.SectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    /*
        JPA RELATIONSHIPS
     */
    //UNIDIRECTIONAL
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Child> children = new HashSet<>();

    //UNIDIRECTIONAL
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Employee> employees = new HashSet<>();

    //This method adds child to the child
    public void addChild(Child child) {
        this.children.add(child);
    }

    //This method removes child from the child
    public void removeChild(Child child) {
        this.children.remove(child);
    }

    //This method adds employee to the employee
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    //This method removes employee from the employee
    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

}
