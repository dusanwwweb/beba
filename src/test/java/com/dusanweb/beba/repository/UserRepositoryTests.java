package com.dusanweb.beba.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.dusanweb.beba.enumeration.RoleType;
import com.dusanweb.beba.model.Role;
import com.dusanweb.beba.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    @Order(1)
    @DisplayName("Test case for persisting a new user into the database")
    public void testCreateUser() {
        User user = new User();
        user.setEmail("patrick@gmail.com");
        user.setPassword(passwordEncoder().encode("pass"));
        user.setFirstName("Patrick");
        user.setLastName("Duclos");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    @Order(2)
    @DisplayName("Test case for persisting a new user with Admin role")
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepository.findByName(RoleType.ROLE_ADMIN);

        User user = new User();
        user.setEmail("michel@gmail.com");
        user.setPassword(passwordEncoder().encode("pass"));
        user.setFirstName("Michel");
        user.setLastName("Drucker");
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("Test case for updating an existing user by adding two roles")
    public void testAddRoleToExistingUser() {
        User user = userRepository.findById(1L).get();
        Role roleUser = roleRepository.findByName(RoleType.ROLE_USER);
        Role roleAssistant = roleRepository.findByName(RoleType.ROLE_ASSISTANT);

        user.addRole(roleUser);
        user.addRole(roleAssistant);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }
}