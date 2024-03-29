package com.dusanweb.beba.model;

import com.dusanweb.beba.enumeration.ActivityType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    //Specifies that a persistent property or field should be persisted
    // as a large object to a database-supported large object type.
    //@Column(name = "description", columnDefinition="CLOB", length = 512)
    // @Column(columnDefinition = "TEXT") //for mysql 'text':
    @Lob //for mysql 'longtext':
    @Nullable //A common Spring annotation to declare that annotated elements can be null under some circumstance.
    private String observation;

    //When a new entity gets persisted, Hibernate gets the current timestamp from the VM
    // and sets it as the value of the attribute annotated with @CreationTimestamp.
    // After that, Hibernate will not change the value of this attribute.
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @CreationTimestamp
    private Date startTime;


    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm a")
    @UpdateTimestamp //TODO Changed from Creationtimestamp
    private Date endTime;

    //TODO Qui a ajouté le post
    //private String createdBy;

    //BIDIRECTIONAL --> PODREDJENA STRANA
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "notebook_id", nullable = false)
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Post )) return false;
        return id != null && id.equals(((Post) obj).getId());
    }

    public void setPost(Notebook notebook) {
    }
}
