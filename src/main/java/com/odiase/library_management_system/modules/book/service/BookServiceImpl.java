package com.odiase.library_management_system.modules.book.service;

import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
import com.odiase.library_management_system.common.util.PaginationUtils;
import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.book.mapper.BookMapper;
import com.odiase.library_management_system.modules.book.repository.BookRepository;
import com.odiase.library_management_system.modules.genre.entity.Genre;
import com.odiase.library_management_system.modules.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        return bookMapper.toResponseDto(book);
    }

    @Override
    public BookResponseDto getBookByTitle(String title) {
        Book book =  bookRepository.findByTitleIgnoreCase(title).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        return bookMapper.toResponseDto(book);
    }

    @Override
    public Page<BookResponseDto> getAllBooks(Integer page, Integer size, String sortBy, String sortDir) {
        Pageable pageable = PaginationUtils.createPageRequest(page, size, sortBy, sortDir);

        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookResponseDto> content = bookMapper.toResponseDtoList(bookPage.getContent());

        return new PageImpl<>(content, pageable, bookPage.getTotalElements());
    }

    @Override
    public List<BookResponseDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return bookMapper.toResponseDtoList(books);
    }

    @Override
    public List<BookResponseDto> getBooksByGenreId(Long genreId) {
        List<Book> books = bookRepository.findByGenreId(genreId);
        return bookMapper.toResponseDtoList(books);
    }

    @Override
    public List<BookResponseDto> getAvailableBooks() {
        List<Book> books = bookRepository.findAllAvailableBooks();
        return bookMapper.toResponseDtoList(books);
    }

    private boolean bookExist(Long id) {
        return true;
    }

    @Override
    public BookResponseDto addBook(AddBookRequestDto addBookRequest) {
        Genre genre = genreRepository.findById(addBookRequest.getGenreId()).orElseThrow(() -> new ResourceNotFoundException("Genre not found!"));
        Book book = bookMapper.toEntity(addBookRequest, genre);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long bookId, UpdateBookRequestDto updateBookRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        bookMapper.updateBookFromDto(updateBookRequest, book);

        Genre genre = genreRepository.findById(updateBookRequest.getGenreId()).orElseThrow(() -> new ResourceNotFoundException("Genre not found!"));
        book.setGenre(genre);

        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) throw new ResourceNotFoundException("Book with ID " + id + " not found");
        bookRepository.deleteById(id);
    }
}
