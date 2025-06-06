package com.odiase.library_management_system.modules.borrowRecord.controller;

import com.odiase.library_management_system.common.dto.response.PagedResponse;
import com.odiase.library_management_system.common.response.ApiResponse;
import com.odiase.library_management_system.common.util.PaginationUtils;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.AddBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.UpdateBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.response.BorrowRecordResponseDto;
import com.odiase.library_management_system.modules.borrowRecord.service.BorrowRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/borrowRecords")
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllBorrowRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Integer adjustedPage = PaginationUtils.toZeroBasedPage(page);

        Page<BorrowRecordResponseDto> borrowRecordsPage = borrowRecordService.getAllBorrowRecords(adjustedPage, size, sortBy, sortDir);
        PagedResponse<BorrowRecordResponseDto> response = new PagedResponse<>(
                borrowRecordsPage.getContent(),
                borrowRecordsPage.getNumber() + 1,
                borrowRecordsPage.getSize(),
                borrowRecordsPage.getTotalElements(),
                borrowRecordsPage.getTotalPages(),
                borrowRecordsPage.isLast()
        );

        return ResponseEntity.ok(new ApiResponse("Successfully fetched all books borrowed records", response));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse> getBorrowRecordsByBookId(@PathVariable Long bookId) {
        List<BorrowRecordResponseDto> borrowRecord = borrowRecordService.getBorrowRecordsByBookId(bookId);
        return ResponseEntity.ok(new ApiResponse("Successfully fetched borrowed records for book ID " + bookId, borrowRecord));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getBorrowRecordsByUserId(@PathVariable Long userId) {
        List<BorrowRecordResponseDto> borrowRecord = borrowRecordService.getBorrowRecordsByUserId(userId);
        return ResponseEntity.ok(new ApiResponse("Successfully fetched borrowed records for user ID " + userId, borrowRecord));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> borrowBook(@Valid @RequestBody AddBorrowRecordRequestDto addBorrowRequest) {
        BorrowRecordResponseDto borrowRecord = borrowRecordService.borrowBook(addBorrowRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully borrowed book!", borrowRecord));
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<ApiResponse> returnBook(@PathVariable Long recordId, @Valid @RequestBody UpdateBorrowRecordRequestDto returnBorrowRequest) {
        BorrowRecordResponseDto borrowRecord = borrowRecordService.returnBook(recordId, returnBorrowRequest);
        return ResponseEntity.ok(new ApiResponse("Successfully returned books", borrowRecord));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<ApiResponse> deleteBrrowRecordById(@PathVariable Long recordId) {
        borrowRecordService.deleteBorrowRecordById(recordId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted borrow record", null));
    }
}
