package com.library.library.mapper;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.model.Author;
import com.library.library.domain.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookEntityToBookMapper implements Converter<BookEntity, Book> {

    private final AuthorOnlyEntityToAuthorMapper authorOnlyEntityToAuthorMapper;

    @Override
    public Book convert(BookEntity source) {
        return Book.builder()
                    .id(source.getId())
                    .lang(source.getLang())
                    .numberOfPages(source.getNumberOfPages())
                    .releaseYear(source.getReleaseYear())
                    .title(source.getTitle())
                    .author(mapAuthor(source.getAuthor()))
                    .build();
    }

    private Author mapAuthor(AuthorEntity author) {
        return authorOnlyEntityToAuthorMapper.convert(author);
    }
}
