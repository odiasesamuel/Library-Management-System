package com.odiase.library_management_system.modules.book.mapper;

import com.odiase.library_management_system.modules.book.dto.request.AddBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.request.UpdateBookRequestDto;
import com.odiase.library_management_system.modules.book.dto.response.BookResponseDto;
import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.genre.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "genre.name", target = "genreName")
    BookResponseDto toResponseDto(Book book);

    List<BookResponseDto> toResponseDtoList(List<Book> books);

    Book toEntity(AddBookRequestDto addBookRequest);

    // Custom method to set genre on an existing Book entity
    default Book toEntity(AddBookRequestDto dto, Genre genre) {
        Book book = toEntity(dto);
        book.setGenre(genre);
        return book;
    }

    void updateBookFromDto(UpdateBookRequestDto updateBookRequest, @MappingTarget Book book);
}
