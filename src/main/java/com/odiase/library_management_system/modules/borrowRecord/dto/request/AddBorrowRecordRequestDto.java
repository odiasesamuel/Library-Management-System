package com.odiase.library_management_system.modules.borrowRecord.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddBorrowRecordRequestDto {
    @NotNull(message = "Book ID is required")
    private Long bookId;

    @NotNull(message = "Return date is required")
    @PastOrPresent(message = "Borrow date cannot be in the future")
    private LocalDate borrowDate;
}
