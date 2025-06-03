package com.odiase.library_management_system.modules.user.repository;

import com.odiase.library_management_system.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
