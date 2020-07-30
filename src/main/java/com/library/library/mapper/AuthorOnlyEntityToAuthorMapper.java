package com.library.library.mapper;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorOnlyEntityToAuthorMapper implements Converter<AuthorEntity, Author> {
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
}
