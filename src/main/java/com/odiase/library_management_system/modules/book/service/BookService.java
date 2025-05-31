package com.odiase.library_management_system.modules.book.service;

import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.entity.Book;

import java.util.List;

public interface BookService {
    BookResponseDto getBookById(Long id);
    BookResponseDto getBookByTitle(String title);
    List<BookResponseDto> getAllBooks();
    List<BookResponseDto> getBooksByAuthor(String author);
    List<BookResponseDto> getBooksByGenreId(Long genreId);
    List<BookResponseDto> getAvailableBooks();

    BookResponseDto addBook(AddBookRequestDto addBookRequest);
    BookResponseDto updateBook(Long bookId, UpdateBookRequestDto updateBookRequest);

    void deleteBookById(Long id);
}
