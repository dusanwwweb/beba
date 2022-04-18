package com.dusanweb.beba.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
//The UNIQUE constraint ensures that all values in a column are different.
public class User {
//public abstract class User { //commented because can not instantiate the abstract user class (in AuthService)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Name is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    //The string has to be a well-formed email address.
    // Exact semantics of what makes up a valid email address are left to
    // Jakarta Bean Validation providers. Accepts CharSequence.
    //null elements are considered valid.
    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    //The annotated element must not be null and must contain at least one non-whitespace character.
    @Size(min = 6, max = 20)
    @NotBlank(message = "Password is required")
    private String password;

    @CreationTimestamp
    @Column(name = "created")
    private Date created;

    //private Boolean enabled;

    /*
        JPA RELATIONSHIPS
    */
    /*
     User class has a Set of Roles but the Role class doesn’t have any references of User.
     And by default, no cascade operations on a @ManyToMany relationship – that means updating
     a User object won’t change the associated Role objects.
     */
    //UNIDIRECTIONAL --> OWNING SIDE
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //This method adds role to the user
    public void addRole(Role role) {
        this.roles.add(role);
    }

    //This method removes role from the user
    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}
