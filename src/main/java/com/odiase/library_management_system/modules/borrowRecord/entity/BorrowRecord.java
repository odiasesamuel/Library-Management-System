package com.odiase.library_management_system.modules.borrowRecord.entity;

import com.odiase.library_management_system.modules.book.entity.Book;
import com.odiase.library_management_system.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
    @Id
    @SequenceGenerator(name = "borrow_sequence", sequenceName = "borrow_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate borrowDate;

    private LocalDate returnDate;

    @PrePersist
    public void prePersist() {
        if (this.borrowDate == null) {
            this.borrowDate = LocalDate.now();
        }
    }
}
