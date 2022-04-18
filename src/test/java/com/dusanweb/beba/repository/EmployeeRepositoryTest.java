package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Employee;
import com.dusanweb.beba.model.Notebook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NotebookRepository notebookRepository;


    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Test case for persisting a new employee into the database")
    public void testCreateEmployee() {
        Employee employee = new Employee();

        Notebook notebook = notebookRepository.findById(1L).get();

        employee.addNotebook(notebook);

        Employee savedEmployee = employeeRepository.save(employee);

    }
}
