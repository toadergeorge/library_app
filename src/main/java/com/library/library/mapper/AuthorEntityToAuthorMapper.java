package com.library.library.mapper;
import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.model.Author;
import com.library.library.domain.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthorEntityToAuthorMapper implements Converter<AuthorEntity, Author> {

    private final BookEntityToBookMapper bookEntityToBookMapper;


    @Override
    public Author convert(AuthorEntity source) {
        return Author.builder()
                    .id(source.getId())
                    .firstName(source.getFirstName())
                    .lastName(source.getLastName())
                    .nationality(source.getNationality())
                    .age(source.getAge())
                    .build();
    }

    private List<Book> mapBookList(List<BookEntity> books) {
        return books.stream()
                .map(bookEntityToBookMapper::convert)
                .collect(Collectors.toList());
    }
}
