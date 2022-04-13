package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.AllergyType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    /*
    The @Lob annotation specifies that the database should store the property as Large Object.
    The columnDefinition in the @Column annotation defines the column type for the property.
    Since we're going to save byte array, we're using BLOB.
     */
    @Lob
    @Column(name = "profilePhoto", columnDefinition="BLOB", length=1000)
    private byte[] profilePhoto;
    //https://i.postimg.cc/k4nN81BW/newborn.jpg

    @UpdateTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate updated;

    /*
        JPA RELATIONSHIPS
     */

    //BIDIRECTIONAL
    //ManyToOne on the child side & OnToMany on the parent side
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebook_id", nullable = false)
    private Notebook notebook;


    /*
    //UNIDIRECTIONAL
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notebook_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Notebook notebook;


 */
    /*
    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents = new HashSet<>();

     User class has a Set of Roles but the Role class doesn’t have any references of User.
     And by default, no cascade operations on a @ManyToMany relationship – that means updating
     a User object won’t change the associated Role objects.

     */
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
}
