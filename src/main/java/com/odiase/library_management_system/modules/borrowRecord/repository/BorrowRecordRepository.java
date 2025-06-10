package com.odiase.library_management_system.modules.borrowRecord.repository;

import com.odiase.library_management_system.modules.borrowRecord.entity.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    @EntityGraph(attributePaths = {"user", "book"})
    List<BorrowRecord> findByBookId(Long bookId);

    @EntityGraph(attributePaths = {"user", "book"})
    Page<BorrowRecord> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user", "book"})
    List<BorrowRecord> findByUserId(Long userId);
}
