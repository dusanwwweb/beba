package com.dusanweb.beba.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

    private String name;

    //When a new entity gets persisted, Hibernate gets the current timestamp from the VM
    // and sets it as the value of the attribute annotated with @CreationTimestamp.
    // After that, Hibernate will not change the value of this attribute.
    @CreationTimestamp
    @Column(name = "created")
    private Date created;

    /*
        JPA RELATIONSHIPS
     */

    //BIDIRECTIONAL --> GLAVNA STRANA
    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            //cascade = CascadeType.REMOVE, //If the parent entity is removed from the current persistence context,
            // the related entity will also be removed.
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "notebook"
    )
    private Set<Post> posts = new HashSet<>();


    //The parent entity, NOTEBOOK, features two utility methods (e.g. addPost and removePost)
    // which are used to synchronize both sides of the bidirectional association
    public void addPost(Post post) {
        this.posts.add(post);
        //post.setPost(this);
    }
    public void removePost(Post post) {
        this.posts.remove(post);
        //post.setPost(null);
    }

}
