package com.vijfmusketiers.ProjectB.authentication.user.repository;

import com.vijfmusketiers.ProjectB.authentication.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String name);
    User findByid(Long id);
}
