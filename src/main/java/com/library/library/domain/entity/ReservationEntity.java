package com.library.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reservation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String status;

    private String deliveryAddress;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
