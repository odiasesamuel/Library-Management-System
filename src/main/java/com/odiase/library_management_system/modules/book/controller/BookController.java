package com.odiase.library_management_system.modules.book.controller;

import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
import com.odiase.library_management_system.common.response.ApiResponse;
import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/books")
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse("success", books));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable Long bookId) {
        BookResponseDto book = bookService.getBookById(bookId);
        return ResponseEntity.ok(new ApiResponse("success", book));

    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> getBookByTitle(@RequestParam String title) {
        BookResponseDto book = bookService.getBookByTitle(title);
        return ResponseEntity.ok(new ApiResponse("success", book));
    }

    @GetMapping("author/{author}")
    public ResponseEntity<ApiResponse> getBooksByAuthor(@PathVariable String author){
       List<BookResponseDto> books = bookService.getBooksByAuthor(author);
       return ResponseEntity.ok(new ApiResponse("success", books));
    }

    @GetMapping("genre/{genreId}")
    public ResponseEntity<ApiResponse> getBooksByGenreId(@PathVariable Long genreId) {
        List<BookResponseDto> books = bookService.getBooksByGenreId(genreId);
        return ResponseEntity.ok(new ApiResponse("success", books));
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse> getAvailableBooks() {
        List<BookResponseDto> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(new ApiResponse("success", books));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addBook(@Valid @RequestBody AddBookRequestDto addBookRequest) {
        BookResponseDto book = bookService.addBook(addBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully added book!", book));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookRequestDto updateBookRequest) {
        BookResponseDto book = bookService.updateBook(bookId, updateBookRequest);
        return ResponseEntity.ok(new ApiResponse("Successfully updated book", book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted Book", null));
    }
}
