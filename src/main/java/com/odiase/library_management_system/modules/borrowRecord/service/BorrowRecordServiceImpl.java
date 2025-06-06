package com.odiase.library_management_system.modules.borrowRecord.service;

import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
import com.odiase.library_management_system.common.util.PaginationUtils;
import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.book.repository.BookRepository;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.AddBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.UpdateBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.response.BorrowRecordResponseDto;
import com.odiase.library_management_system.modules.borrowRecord.entity.BorrowRecord;
import com.odiase.library_management_system.modules.borrowRecord.mapper.BorrowRecordMapper;
import com.odiase.library_management_system.modules.borrowRecord.repository.BorrowRecordRepository;
import com.odiase.library_management_system.modules.user.entity.User;
import com.odiase.library_management_system.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    @Override
    public Page<BorrowRecordResponseDto> getAllBorrowRecords(Integer page, Integer size, String sortBy, String sortDir) {
        Pageable pageable = PaginationUtils.createPageRequest(page, size, sortBy, sortDir);

        Page<BorrowRecord> borrowRecordPage = borrowRecordRepository.findAll(pageable);
        List<BorrowRecordResponseDto> content = borrowRecordMapper.toResponseDtoList(borrowRecordPage.getContent());

        return new PageImpl<>(content, pageable, borrowRecordPage.getTotalElements());
    }

    @Override
    public List<BorrowRecordResponseDto> getBorrowRecordsByBookId(Long bookId) {
       List<BorrowRecord> borrowRecords = borrowRecordRepository.findByBookId(bookId);
       return borrowRecordMapper.toResponseDtoList(borrowRecords);
    }

    @Override
    public List<BorrowRecordResponseDto> getBorrowRecordsByUserId(Long userId) {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findByBookId(userId);
        return borrowRecordMapper.toResponseDtoList(borrowRecords);
    }

    @Transactional
    @Override
    public BorrowRecordResponseDto borrowBook(AddBorrowRecordRequestDto addBorrowRequest) {
        User user = userRepository.findById(addBorrowRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User is not found!"));

        Book book = bookRepository.findById(addBorrowRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        if (book.getAvailableCopies() <= 0) throw new IllegalStateException("No available copies for this book.");
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord borrowRecord = borrowRecordMapper.toEntity(addBorrowRequest, user, book);
        BorrowRecord savedBorrowRecord = borrowRecordRepository.save(borrowRecord);

        return borrowRecordMapper.toResponseDto(savedBorrowRecord);
    }

    @Transactional
    @Override
    public BorrowRecordResponseDto returnBook(Long borrowRecordId, UpdateBorrowRecordRequestDto returnBorrowRequest) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new ResourceNotFoundException("Borrow Record not found"));
        borrowRecordMapper.updateBorrowRecordFromDto(returnBorrowRequest, borrowRecord);

        User user = userRepository.findById(returnBorrowRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User is not found!"));

        Book book = bookRepository.findById(returnBorrowRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        borrowRecord.setUser(user);
        borrowRecord.setBook(book);

        BorrowRecord savedBorrowRecord = borrowRecordRepository.save(borrowRecord);

        return borrowRecordMapper.toResponseDto(savedBorrowRecord);
    }

    @Override
    public void deleteBorrowRecordById(Long borrowRecordId) {

        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new ResourceNotFoundException("Borrow record with ID " + borrowRecordId + " not found"));

        if (borrowRecord.getReturnDate() == null) throw new IllegalStateException("You can't delete record for a book that hasn't been returned");

        borrowRecordRepository.deleteById(borrowRecordId);
    }
}
