package com.odiase.library_management_system.modules.user.repository;

import com.odiase.library_management_system.modules.user.dto.request.RegisterUserRequestDto;
import com.odiase.library_management_system.modules.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"roles"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByEmail(String email);
}
