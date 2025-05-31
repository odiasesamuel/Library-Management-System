package com.odiase.library_management_system.modules.book.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBookRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Genre ID is required")
    private Long genreId;

    @NotNull(message = "Available copies must be specified")
    @Min(value = 0, message = "Available copies must be zero or more")
    private Integer availableCopies;
}
