package com.library.library.mapper;

import com.library.library.domain.entity.AuthorEntity;
import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.ReservationEntity;
import com.library.library.domain.entity.UserEntity;
import com.library.library.domain.model.Author;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.Reservation;
import com.library.library.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationEntityToReservationMapper implements Converter<ReservationEntity, Reservation> {

    private final UserOnlyEntityToUserMapper userOnlyEntityToUserMapper;

    @Override
    public Reservation convert(ReservationEntity source) {
        return Reservation.builder()
                .id(source.getId())
                .deliveryAddress(source.getDeliveryAddress())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .user(mapUser(source.getUser()))
                .status(source.getStatus())
                .build();
    }

    private User mapUser(UserEntity user) {
        return userOnlyEntityToUserMapper.convert(user);
    }
}
