package com.odiase.library_management_system.modules.borrowRecord.service;

import com.odiase.library_management_system.modules.borrowRecord.dto.request.AddBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.UpdateBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.response.BorrowRecordResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BorrowRecordService {
    Page<BorrowRecordResponseDto> getAllBorrowRecords(Integer page, Integer size, String sortBy, String sortDi); /* Admin access */
    List<BorrowRecordResponseDto> getBorrowRecordsByBookId(Long bookId);
    List<BorrowRecordResponseDto> getBorrowRecordsByUserId(Long userId);

    BorrowRecordResponseDto borrowBook(AddBorrowRecordRequestDto addBorrowRequest);
    BorrowRecordResponseDto returnBook(Long borrowRecordId, UpdateBorrowRecordRequestDto returnBorrowRequest);
    void deleteBorrowRecordById(Long borrowRecordId);

    boolean isOwner(Long recordId, Long userId);

}
