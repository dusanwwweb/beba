package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notebook {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Lob //for mysql 'longtext':
    //Specifies that a persistent property or field should be persisted
    // as a large object to a database-supported large object type.
    //@Column(name = "description", columnDefinition="CLOB", length = 512)
    // @Column(columnDefinition = "TEXT") //for mysql 'text':
    @Nullable //A common Spring annotation to declare that annotated elements can be null under some circumstance.
    private String observation;

    //When a new entity gets persisted, Hibernate gets the current timestamp from the VM
    // and sets it as the value of the attribute annotated with @CreationTimestamp.
    // After that, Hibernate will not change the value of this attribute.

    @CreationTimestamp
    private LocalDateTime time;

    /*
        JPA RELATIONSHIPS
     */
    @ManyToMany(mappedBy = "notebooks")
    private Set<Employee> employees = new HashSet<>();

    //The second best way is to define a bidirectional association with a
    // @OneToMany annotation on the parent side of the relationship and a
    // @ManyToOne annotation on the child side of the relationship.


     //BIDIRECTIONAL
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notebook")
    private Set<Child> childSet = new HashSet<>();



}
