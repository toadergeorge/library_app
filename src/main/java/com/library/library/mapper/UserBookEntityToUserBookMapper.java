package com.library.library.mapper;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.UserBookEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.User;
import com.library.library.domain.model.UserBook;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserBookEntityToUserBookMapper implements Converter<UserBookEntity, UserBook> {

    private final UserEntityToUserMapper userEntityToUserMapper;
    private final BookEntityToBookMapper bookEntityToBookMapper;

    @Override
    public UserBook convert(UserBookEntity source) {
        return UserBook.builder()
                    .id(source.getId())
                    .status(source.getStatus())
                    .user(mapUser(source.getUser()))
                    .book(mapBook(source.getBook()))
                    .build();
    }

    private User mapUser(UserEntity user) {
        return userEntityToUserMapper.convert(user);
    }
    private Book mapBook(BookEntity entity) {
        return bookEntityToBookMapper.convert(entity);
    }
}
