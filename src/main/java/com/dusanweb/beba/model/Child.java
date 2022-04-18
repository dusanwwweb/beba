package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.AllergyType;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Child {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderColumn()
    @NotBlank(message = "Name is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @JsonFormat(pattern="dd/MM/yyyy")
    @NotBlank(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private Float weight;

    @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "enum('GLUTEN','COWS_MILK','NUTS','FISH','SOYBEAN','EGG')")
    private AllergyType allergyType;

    private String profilePhoto;

    @UpdateTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate updated;

    /*
        JPA RELATIONSHIPS
     */

    //UNIDIRECTIONAL
    @OneToOne(fetch = FetchType.EAGER)
    //@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    //UNIDIRECTIONAL
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "child_parent",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private Set<Parent> parents = new HashSet<>();

    //This method adds parent to the child
    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    //This method removes parent from the child
    public void removeParent(Parent parent) {
        this.parents.remove(parent);
    }
}
