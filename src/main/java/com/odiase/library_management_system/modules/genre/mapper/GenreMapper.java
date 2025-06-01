package com.odiase.library_management_system.modules.genre.mapper;

import com.odiase.library_management_system.modules.genre.dto.request.GenreRequestDto;
import com.odiase.library_management_system.modules.genre.dto.response.GenreResponseDto;
import com.odiase.library_management_system.modules.genre.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponseDto toResponseDto(Genre genre);

    List<GenreResponseDto> toResponseDtoList(List<Genre> genres);

    Genre toEntity(GenreRequestDto addGenreRequest);

    void updateGenreFromDto(GenreRequestDto updateGenreRequest, @MappingTarget Genre genre);
}
