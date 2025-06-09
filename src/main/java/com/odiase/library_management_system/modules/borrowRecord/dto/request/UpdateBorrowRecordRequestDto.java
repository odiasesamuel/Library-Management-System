package com.odiase.library_management_system.modules.borrowRecord.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBorrowRecordRequestDto {
    @NotNull(message = "Book ID is required")
    private Long bookId;

    @PastOrPresent(message = "Borrow date cannot be in the future")
    private LocalDate borrowDate;

    @NotNull(message = "Return date is required")
    @PastOrPresent(message = "Return date cannot be in the future")
    private LocalDate returnDate;
}
