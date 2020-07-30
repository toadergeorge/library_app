package com.library.library.mapper;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookToBookEntityMapper implements Converter<Book, BookEntity> {

    private final AuthorToAuthorEntityMapper authorToAuthorEntityMapper;

    @Override
    public BookEntity convert(Book source) {
        return BookEntity.builder()
                .title(source.getTitle())
                .lang(source.getLang())
                .numberOfPages(source.getNumberOfPages())
                .releaseYear(source.getReleaseYear())
                .author(authorToAuthorEntityMapper.convert(source.getAuthor()))
                .build();
    }
}
