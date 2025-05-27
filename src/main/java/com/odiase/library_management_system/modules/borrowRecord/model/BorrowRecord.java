package com.odiase.library_management_system.modules.borrowRecord.model;

import com.odiase.library_management_system.modules.book.model.Book;
import com.odiase.library_management_system.modules.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BorrowRecord {
    @Id
    @SequenceGenerator(name = "borrow_sequence", sequenceName = "borrow_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Date borrowDate;
    private Date returnDate;
}
