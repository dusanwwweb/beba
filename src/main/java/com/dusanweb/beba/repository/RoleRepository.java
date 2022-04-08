package com.dusanweb.beba.repository;

import com.dusanweb.beba.enumeration.RoleType;
import com.dusanweb.beba.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleType name);
}
