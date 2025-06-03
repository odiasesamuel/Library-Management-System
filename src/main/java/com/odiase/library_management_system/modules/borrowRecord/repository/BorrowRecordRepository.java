package com.odiase.library_management_system.modules.borrowRecord.repository;

import com.odiase.library_management_system.modules.borrowRecord.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByBookId(Long bookId);

    List<BorrowRecord> findByUserId(Long userId);
}
