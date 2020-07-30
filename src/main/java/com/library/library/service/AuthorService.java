package com.library.library.service;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.model.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.AuthorEntityToAuthorMapper;
import com.library.library.mapper.AuthorToAuthorEntityMapper;
import com.library.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    private final AuthorEntityToAuthorMapper authorEntityToAuthorMapper;
    private final AuthorToAuthorEntityMapper authorToAuthorMapper;

    public Author findById(long authorId) {
        return repository.findById(authorId)
                .map(authorEntityToAuthorMapper::convert)
                .orElseThrow(() -> new AuthorNotFoundException("The album with id provided cannot be found"));
    }

    public List<Author> getAll() {
        return repository.getAll()
                .stream()
                .map(authorEntityToAuthorMapper::convert)
                .collect(Collectors.toList());
    }

    public Author create(Author author) {
        AuthorEntity authorEntity = authorToAuthorMapper.convert(author);

        AuthorEntity savedEntity = repository.save(authorEntity);
        return authorEntityToAuthorMapper.convert(savedEntity);
    }

    @Transactional
    public void updateTransactional(Author author) {
        AuthorEntity existingEntity = repository.findById(author.getId())
                .orElseThrow(() -> new AuthorNotFoundException("The author with id provided cannot be found"));

        updateFields(existingEntity, author);
    }

    public void delete(Long id) {
        AuthorEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The author with id provided cannot be found"));

        repository.delete(existingEntity);
    }

    private void updateFields(AuthorEntity existingEntity, Author author) {
        existingEntity.setAge(author.getAge());
        existingEntity.setFirstName(author.getFirstName());
        existingEntity.setLastName(author.getLastName());
        existingEntity.setNationality(author.getNationality());
    }
}
