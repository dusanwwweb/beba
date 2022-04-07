package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.AllergyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    //@JsonFormat(pattern = "yyyy-MM-dd")
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
    @Column(name = "profilePhoto", columnDefinition="BLOB")
    private byte[] profilePhoto;

    @UpdateTimestamp
    private LocalDate updated;

    /*
        JPA RELATIONSHIPS
     */
    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents = new HashSet<>();

    //BIDIRECTIONAL
    //ManyToOne on the child side & OnToMany on the parent side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebook_id", nullable = false)
    private Notebook notebook;


    /*
    //UNIDIRECTIONAL
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notebook_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Notebook notebook;

     */
}
