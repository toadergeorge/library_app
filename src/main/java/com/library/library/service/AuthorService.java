package com.library.library.service;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.model.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.AuthorEntityToAuthorMapper;
import com.library.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    private final AuthorEntityToAuthorMapper authorEntityToAuthorMapper;

    public Author findById(long authorId) {
        return repository.findById(authorId)
                .map((AuthorEntity authorEntity) -> authorEntityToAuthorMapper.convert(authorEntity))
                .orElseThrow(() -> new AuthorNotFoundException("The album with id provided cannot be found"));
    }

}
