package com.library.library.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Reservation {

    private Long id;

    private User user;

    private String status;

    private String deliveryAddress;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
