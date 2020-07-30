package com.library.library.mapper;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorToAuthorEntityMapper implements Converter<Author, AuthorEntity> {

    @Override
    public AuthorEntity convert(Author source) {
        return AuthorEntity.builder()
                .id(source.getId())
                .age(source.getAge())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .nationality(source.getNationality())
                .build();
    }
}
