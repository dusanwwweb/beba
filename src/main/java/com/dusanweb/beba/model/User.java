package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class User {
//public abstract class User { //commented because can not instantiate the abstract user class (in AuthService)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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

    @UpdateTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate updated;

    /*
        JPA RELATIONSHIPS
    */
    /*
     User class has a Set of Roles but the Role class doesn’t have any references of User.
     And by default, no cascade operations on a @ManyToMany relationship – that means updating
     a User object won’t change the associated Role objects.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //This method adds role to the user
    public void addRole(Role role) {
        this.roles.add(role);
    }
}
