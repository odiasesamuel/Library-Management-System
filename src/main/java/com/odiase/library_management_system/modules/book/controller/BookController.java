package com.odiase.library_management_system.modules.book.controller;

import com.odiase.library_management_system.common.dto.response.PagedResponse;
import com.odiase.library_management_system.common.response.ApiResponse;
import com.odiase.library_management_system.common.util.PaginationUtils;
import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/books")
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllBooks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Integer adjustedPage = PaginationUtils.toZeroBasedPage(page);

        Page<BookResponseDto> booksPage = bookService.getAllBooks(adjustedPage, size, sortBy, sortDir);
        PagedResponse<BookResponseDto> response = new PagedResponse<>(
                booksPage.getContent(),
                booksPage.getNumber() + 1,
                booksPage.getSize(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages(),
                booksPage.isLast()
        );
        return ResponseEntity.ok(new ApiResponse("success", response));
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse> addBook(@Valid @RequestBody AddBookRequestDto addBookRequest) {
        BookResponseDto book = bookService.addBook(addBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully added book!", book));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookRequestDto updateBookRequest) {
        BookResponseDto book = bookService.updateBook(bookId, updateBookRequest);
        return ResponseEntity.ok(new ApiResponse("Successfully updated book", book));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted Book", null));
    }
}
