package com.library.library.service;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.model.Book;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.AuthorToAuthorEntityMapper;
import com.library.library.mapper.BookEntityToBookMapper;
import com.library.library.mapper.BookToBookEntityMapper;
import com.library.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final BookEntityToBookMapper bookEntityToBookMapper;
    private final BookToBookEntityMapper bookToBookEntityMapper;
    private final AuthorToAuthorEntityMapper authorToAuthorEntityMapper;

    public Book findById(long bookId) {
        return repository.findById(bookId)
                .map(bookEntityToBookMapper::convert)
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));
    }

    public List<Book> getAll() {
        return repository.getAll()
                .stream()
                .map(bookEntityToBookMapper::convert)
                .collect(Collectors.toList());
    }

    public Book create(Book book) {
        BookEntity bookEntity = bookToBookEntityMapper.convert(book);

        BookEntity savedEntity = repository.save(bookEntity);
        return bookEntityToBookMapper.convert(savedEntity);
    }

    @Transactional
    public void updateTransactional(Book book) {
        BookEntity existingEntity = repository.findById(book.getId())
                .orElseThrow(() -> new AuthorNotFoundException("The book with id provided cannot be found"));

        updateFields(existingEntity, book);
    }

    private void updateFields(BookEntity existingEntity, Book book) {
        existingEntity.setReleaseYear(book.getReleaseYear());
        existingEntity.setLang(book.getLang());
        existingEntity.setNumberOfPages(book.getNumberOfPages());
        existingEntity.setTitle(book.getTitle());
        existingEntity.setAuthor(authorToAuthorEntityMapper.convert(book.getAuthor()));
    }

    public void delete(Long id) {
        BookEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The book with id provided cannot be found"));

        repository.delete(existingEntity);
    }
}
