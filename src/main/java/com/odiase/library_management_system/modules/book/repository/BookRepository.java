package com.odiase.library_management_system.modules.book.repository;

import com.odiase.library_management_system.modules.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByGenreId(Long genreId);

    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAllAvailableBooks();
}
