package com.library.library.service;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.UserBookEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.User;
import com.library.library.exception.UserBookNotFoundException;
import com.library.library.mapper.*;
import com.library.library.repository.UserBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBookService {

    private final UserBookRepository repository;

    private final UserToUserEntityMapper userToUserEntityMapper;
    private final BookToBookEntityMapper bookToBookEntityMapper;

    /**
     * @param book
     * @param user
     */
    public void create(Book book, User user) {

        BookEntity bookEntity = bookToBookEntityMapper.convert(book);
        UserEntity userEntity = userToUserEntityMapper.convert(user);

        bookEntity.setId(book.getId());
        userEntity.setId(user.getId());

        UserBookEntity userBookEntity = UserBookEntity.builder().book(bookEntity).user(userEntity).status("in_stock").build();

        repository.save(userBookEntity);
    }

    public void delete(Long userBookId) {
        UserBookEntity userBookEntity = repository.findById(userBookId)
                .orElseThrow(() -> new UserBookNotFoundException("The userBook with id provided cannot be found"));

        repository.delete(userBookEntity);
    }
}
