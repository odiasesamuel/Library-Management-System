package com.odiase.library_management_system.modules.genre.repository;

import com.odiase.library_management_system.modules.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
