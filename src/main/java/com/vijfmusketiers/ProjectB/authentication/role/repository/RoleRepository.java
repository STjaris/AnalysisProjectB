package com.vijfmusketiers.ProjectB.authentication.role.repository;

import com.vijfmusketiers.ProjectB.authentication.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
