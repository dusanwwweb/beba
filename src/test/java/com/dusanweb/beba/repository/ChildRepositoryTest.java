package com.dusanweb.beba.repository;

import com.dusanweb.beba.enumeration.AllergyType;
import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Parent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ChildRepositoryTest {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private NotebookRepository notebookRepository;


    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Test case for persisting a new child into the database")
    public void testCreateChild() {

        Parent parent = parentRepository.findById(1L).get();

        Child child = new Child();
        child.setFirstName("Axel");
        child.setLastName("VOISIN");
        child.setAddress("119 rue Manin");
        child.setCity("Paris");
        child.setDateOfBirth(LocalDate.of(2021, 5, 9));
        child.setWeight(9.1F);
        child.setAllergyType(AllergyType.NUTS);
        //child.setProfilePhoto();
        child.setNotebook(notebookRepository.findById(1L).get());

        child.addParent(parent);

        Child savedChild = childRepository.save(child);
        Child existChild = entityManager.find(Child.class, savedChild.getId());

        assertThat(child.getFirstName()).isEqualTo(existChild.getFirstName());
    }
}
