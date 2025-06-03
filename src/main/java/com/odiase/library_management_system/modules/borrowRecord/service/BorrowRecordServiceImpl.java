package com.odiase.library_management_system.modules.borrowRecord.service;

import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
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
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    @Override
    public List<BorrowRecordResponseDto> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findAll();
        return borrowRecordMapper.toResponseDtoList(borrowRecords);
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

    @Override
    public BorrowRecordResponseDto borrowBook(AddBorrowRecordRequestDto addBorrowRequest) {
        User user = userRepository.findById(addBorrowRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User is not found!"));

        Book book = bookRepository.findById(addBorrowRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        BorrowRecord borrowRecord = borrowRecordMapper.toEntity(addBorrowRequest, user, book);
        borrowRecord = borrowRecordRepository.save(borrowRecord);

        return borrowRecordMapper.toResponseDto(borrowRecord);
    }

    @Override
    public BorrowRecordResponseDto returnBook(Long borrowRecordId, UpdateBorrowRecordRequestDto returnBorrowRequest) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new ResourceNotFoundException("Borrow Record not found"));
        borrowRecordMapper.updateBorrowRecordFromDto(returnBorrowRequest, borrowRecord);

        User user = userRepository.findById(returnBorrowRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User is not found!"));

        Book book = bookRepository.findById(returnBorrowRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        borrowRecord.setUser(user);
        borrowRecord.setBook(book);

        borrowRecord = borrowRecordRepository.save(borrowRecord);

        return borrowRecordMapper.toResponseDto(borrowRecord);
    }

    @Override
    public void deleteBrrowRecordById(Long borrowRecordId) {
        if (!borrowRecordRepository.existsById(borrowRecordId)) throw new ResourceNotFoundException("Borrow record with ID " + borrowRecordId + " not found");
        borrowRecordRepository.deleteById(borrowRecordId);
    }
}
