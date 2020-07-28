package com.library.library.mapper;
import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class AuthorEntityToAuthorMapper implements Converter<AuthorEntity, Author> {
    @Override
    public Author convert(AuthorEntity source) {
        return Author.builder()
                    .id(source.getId())
                    .firstName(source.getFirstName())
                    .lastName(source.getLastName())
                    .age(source.getAge())
                    .build();
    }
}
