package com.odiase.library_management_system.modules.genre.model;

import com.odiase.library_management_system.modules.book.model.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    @Id
    @SequenceGenerator(name = "genre_sequence", sequenceName = "genre_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sequence")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
