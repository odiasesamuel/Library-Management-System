package com.odiase.library_management_system.modules.genre.service;

import com.odiase.library_management_system.common.exception.ResourceNotFoundException;
import com.odiase.library_management_system.modules.genre.dto.request.GenreRequestDto;
import com.odiase.library_management_system.modules.genre.dto.response.GenreResponseDto;
import com.odiase.library_management_system.modules.genre.entity.Genre;
import com.odiase.library_management_system.modules.genre.mapper.GenreMapper;
import com.odiase.library_management_system.modules.genre.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GenreServiceImp implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public List<GenreResponseDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toResponseDtoList(genres);
    }

    @Override
    public GenreResponseDto addGenre(GenreRequestDto addGenreRequest) {
        Genre genre = genreMapper.toEntity(addGenreRequest);
        genre = genreRepository.save(genre);
        return genreMapper.toResponseDto(genre);
    }

    @Override
    public GenreResponseDto updateGenre(Long id, GenreRequestDto updateGenreRequest) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre not found!"));
        genreMapper.updateGenreFromDto(updateGenreRequest, genre);

        genre = genreRepository.save(genre);

        return genreMapper.toResponseDto(genre);
    }

    @Override
    public void deleteGenreById(Long id) {
        if (!genreRepository.existsById(id)) throw new ResourceNotFoundException("Genre with ID " + id + " not found");
        genreRepository.deleteById(id);
    }
}
