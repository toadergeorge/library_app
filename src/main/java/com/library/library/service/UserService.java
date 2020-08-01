package com.library.library.service;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Author;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.User;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.*;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BookRepository bookRepository;

    private final UserBookService userBookService;
    private final BookService bookService;

    private final UserEntityToUserMapper userEntityToUserMapper;
    private final UserToUserEntityMapper userToUserMapper;
    private final BookEntityToBookMapper bookEntityToBookMapper;

    public User findById(long userId) {
        return repository.findById(userId)
                .map(userEntityToUserMapper::convert)
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));
    }

    public List<User> getAll() {
        return repository.getAll()
                .stream()
                .map(userEntityToUserMapper::convert)
                .collect(Collectors.toList());
    }

    public User create(User user) {
        UserEntity userEntity = userToUserMapper.convert(user);

        UserEntity savedEntity = repository.save(userEntity);
        return userEntityToUserMapper.convert(savedEntity);
    }

    @Transactional
    public void updateTransactional(User user) {
        UserEntity existingEntity = repository.findById(user.getId())
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));

        updateFields(existingEntity, user);
    }

    @Transactional
    public void createUserBook(long userId, List<Book> userBooks) {

        User user = findById(userId);

        for (Book book : userBooks) {
            if (book.getId() != null) {
                Book dbBook = bookRepository.findById(book.getId())
                        .map(bookEntityToBookMapper::convert)
                        .orElse(null);
                userBookService.create(dbBook, user);

            } else {
                Book newBook = bookService.create(book);
                userBookService.create(newBook, user);
            }
        }
    }

    private void updateFields(UserEntity existingEntity, User user) {
        existingEntity.setFirstName(user.getFirstName());
        existingEntity.setLastName(user.getLastName());
        existingEntity.setCity(user.getCity());
        existingEntity.setCountry(user.getCountry());
        existingEntity.setEmail(user.getEmail());
        existingEntity.setDateOfBirth(user.getDateOfBirth());
    }

    public void delete(Long id) {
        UserEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The usere with id provided cannot be found"));

        repository.delete(existingEntity);
    }
}
