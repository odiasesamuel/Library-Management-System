package com.odiase.library_management_system.modules.borrowRecord.service;

import com.odiase.library_management_system.modules.borrowRecord.dto.request.AddBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.UpdateBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.response.BorrowRecordResponseDto;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecordResponseDto> getAllBorrowRecords(); /* Admin access */
    List<BorrowRecordResponseDto> getBorrowRecordsByBookId(Long bookId);
    List<BorrowRecordResponseDto> getBorrowRecordsByUserId(Long userId);

    BorrowRecordResponseDto borrowBook(AddBorrowRecordRequestDto addBorrowRequest);
    BorrowRecordResponseDto returnBook(Long borrowRecordId, UpdateBorrowRecordRequestDto returnBorrowRequest);
    void deleteBrrowRecordById(Long borrowRecordId);

}
