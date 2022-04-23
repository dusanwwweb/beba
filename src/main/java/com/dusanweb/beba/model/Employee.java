package com.dusanweb.beba.model;


import lombok.*;
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

    //UNIDIRECTIONAL --> GL STR
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_notebook",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "notebook_id")
    )
    private Set<Notebook> notebooks = new HashSet<>();

    //This method adds parent to the child
    public void addNotebook(Notebook notebook) {
        this.notebooks.add(notebook);
    }

    //This method removes parent from the child
    public void removeNotebook(Notebook notebook) {
        this.notebooks.remove(notebook);
    }

}
