package com.odiase.library_management_system.modules.book.dto.response;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String genreName;
    private Integer availableCopies;
}
