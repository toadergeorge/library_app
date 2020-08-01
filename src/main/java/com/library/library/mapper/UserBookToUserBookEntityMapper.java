package com.library.library.mapper;

import com.library.library.domain.entity.UserBookEntity;
import com.library.library.domain.model.UserBook;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserBookToUserBookEntityMapper implements Converter<UserBook, UserBookEntity> {

    private final UserToUserEntityMapper userToUserEntityMapper;
    private final BookToBookEntityMapper bookToBookEntityMapper;

    @Override
    public UserBookEntity convert(UserBook source) {
        return UserBookEntity.builder()
                .id(source.getId())
                .user(userToUserEntityMapper.convert(source.getUser()))
                .status(source.getStatus())
                .book(bookToBookEntityMapper.convert(source.getBook()))
                .build();
    }
}
