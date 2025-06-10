package com.odiase.library_management_system.modules.book.repository;

import com.odiase.library_management_system.modules.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"genre"})
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findByTitleIgnoreCase(String title);

    @EntityGraph(attributePaths = {"genre"})
    List<Book> findByAuthor(String author);

    @EntityGraph(attributePaths = {"genre"})
    List<Book> findByGenreId(Long genreId);

    @EntityGraph(attributePaths = {"genre"})
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAllAvailableBooks();
}
