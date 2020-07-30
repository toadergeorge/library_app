package com.library.library.domain.model;

import com.library.library.domain.entity.BookEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Author {

    private Long id;

    private String firstName;

    private String lastName;

    private String nationality;

    private int age;

    private List<Book> books;
}
