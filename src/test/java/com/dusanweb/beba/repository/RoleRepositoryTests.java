package com.dusanweb.beba.repository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import com.dusanweb.beba.enumeration.RoleType;
import com.dusanweb.beba.model.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // You can use a production type database in tests by adding this annotation
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("Test case for persisting roles into the database")
    public void testCreateRoles() {
        Role user = new Role(RoleType.USER);
        Role admin = new Role(RoleType.ADMIN);
        Role assistant = new Role(RoleType.ASSISTANT);

        roleRepository.saveAll(List.of(user, admin, assistant));

        List<Role> listRoles = roleRepository.findAll();

        assertThat(listRoles.size()).isEqualTo(3);
    }

}