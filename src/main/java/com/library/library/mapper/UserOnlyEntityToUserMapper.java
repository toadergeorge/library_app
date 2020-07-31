package com.library.library.mapper;

import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserOnlyEntityToUserMapper implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity source) {
        return User.builder()
                     .id(source.getId())
                     .firstName(source.getFirstName())
                     .lastName(source.getLastName())
                     .email(source.getEmail())
                     .country(source.getCountry())
                     .city(source.getCity())
                     .dateOfBirth(source.getDateOfBirth())
                     .build();
    }
}
