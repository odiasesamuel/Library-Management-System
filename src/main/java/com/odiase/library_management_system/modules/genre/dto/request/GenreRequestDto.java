package com.odiase.library_management_system.modules.genre.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreRequestDto {
    @NotBlank(message = "name is required")
    private String name;
}
