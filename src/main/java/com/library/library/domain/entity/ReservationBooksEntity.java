package com.library.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reservation_books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationBooksEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservation;

    @ManyToOne
    @JoinColumn(name = "book_user_id")
    private UserBookEntity userBook;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
