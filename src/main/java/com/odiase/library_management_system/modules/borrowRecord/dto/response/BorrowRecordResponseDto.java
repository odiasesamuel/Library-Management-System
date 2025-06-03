package com.odiase.library_management_system.modules.borrowRecord.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecordResponseDto {
    private Long id;
    private String userName;
    private String bookName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
