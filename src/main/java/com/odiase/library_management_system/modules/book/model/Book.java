package com.odiase.library_management_system.modules.book.model;

import com.odiase.library_management_system.modules.borrowRecord.model.BorrowRecord;
import com.odiase.library_management_system.modules.genre.model.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;

    private String title;

    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private String availableCopies;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<BorrowRecord> borrowRecords;
}
