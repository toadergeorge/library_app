package com.library.library.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Book {

    private Long id;

    private String title;

    private Author author;

    private LocalDate releaseYear;

    private String lang;

    private String numberOfPages;
}
