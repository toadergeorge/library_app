package com.library.library.domain.model;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.ReservationEntity;
import com.library.library.domain.entity.UserBookEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationBooks {

    private Long id;

    private String status;

    private ReservationEntity reservation;

    private BookEntity book;

    private UserBookEntity userBook;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
