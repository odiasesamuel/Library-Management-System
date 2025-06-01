package com.odiase.library_management_system.modules.genre.service;

import com.odiase.library_management_system.modules.genre.dto.request.GenreRequestDto;
import com.odiase.library_management_system.modules.genre.dto.response.GenreResponseDto;
import com.odiase.library_management_system.modules.genre.entity.Genre;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAllGenres();
    GenreResponseDto addGenre(GenreRequestDto addGenreRequest);

    GenreResponseDto updateGenre(Long id, GenreRequestDto updateGenreRequest);
    void deleteGenreById(Long id);
}
