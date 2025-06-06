package com.odiase.library_management_system.modules.book.service;

import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookResponseDto getBookById(Long id);
    BookResponseDto getBookByTitle(String title);
    Page<BookResponseDto> getAllBooks(Integer page, Integer size, String sortBy, String sortDir);
    List<BookResponseDto> getBooksByAuthor(String author);
    List<BookResponseDto> getBooksByGenreId(Long genreId);
    List<BookResponseDto> getAvailableBooks();

    BookResponseDto addBook(AddBookRequestDto addBookRequest);
    BookResponseDto updateBook(Long bookId, UpdateBookRequestDto updateBookRequest);

    void deleteBookById(Long id);
}
