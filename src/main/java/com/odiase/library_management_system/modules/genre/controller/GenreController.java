package com.odiase.library_management_system.modules.genre.controller;

import com.odiase.library_management_system.common.response.ApiResponse;
import com.odiase.library_management_system.modules.genre.dto.request.GenreRequestDto;
import com.odiase.library_management_system.modules.genre.dto.response.GenreResponseDto;
import com.odiase.library_management_system.modules.genre.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllGenres() {
        List<GenreResponseDto> genres = genreService.getAllGenres();
        return ResponseEntity.ok(new ApiResponse("success", genres));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse> addGenre(@Valid @RequestBody GenreRequestDto addGenreRequest) {
        GenreResponseDto genreResponse = genreService.addGenre(addGenreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("successfully created genre", genreResponse));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{genreId}")
    public ResponseEntity<ApiResponse> updateGenre(@PathVariable Long genreId, @Valid @RequestBody GenreRequestDto updateGenreRequest) {
        GenreResponseDto genre = genreService.updateGenre(genreId, updateGenreRequest);
        return ResponseEntity.ok(new ApiResponse("successfully updated Genre", genre));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{genreId}")
    public ResponseEntity<ApiResponse> deleteGenre(@PathVariable Long genreId) {
        genreService.deleteGenreById(genreId);
        return ResponseEntity.ok(new ApiResponse("successfully deleted Genre", null));
    }
}
