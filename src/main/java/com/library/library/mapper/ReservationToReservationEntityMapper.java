package com.library.library.mapper;

import com.library.library.domain.entity.ReservationEntity;
import com.library.library.domain.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationToReservationEntityMapper implements Converter<Reservation, ReservationEntity> {

    private final UserToUserEntityMapper userToUserEntityMapper;

    @Override
    public ReservationEntity convert(Reservation source) {
        return ReservationEntity.builder()
                .deliveryAddress(source.getDeliveryAddress())
                .status(source.getStatus())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .user(userToUserEntityMapper.convert(source.getUser()))
                .build();
    }
}
