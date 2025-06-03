package com.odiase.library_management_system.modules.borrowRecord.mapper;

import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.AddBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.request.UpdateBorrowRecordRequestDto;
import com.odiase.library_management_system.modules.borrowRecord.dto.response.BorrowRecordResponseDto;
import com.odiase.library_management_system.modules.borrowRecord.entity.BorrowRecord;
import com.odiase.library_management_system.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.title", target = "bookName")
    BorrowRecordResponseDto toResponseDto(BorrowRecord borrowRecord);

    List<BorrowRecordResponseDto> toResponseDtoList(List<BorrowRecord> borrowRecords);

    BorrowRecord toEntity(AddBorrowRecordRequestDto addBorrowRequest);
    default BorrowRecord toEntity(AddBorrowRecordRequestDto dto, User user, Book book) {
        BorrowRecord borrowRecord = toEntity(dto);
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        return borrowRecord;
    }

    void updateBorrowRecordFromDto(UpdateBorrowRecordRequestDto updateBorrowRequest, @MappingTarget BorrowRecord borrowRecord);
}
