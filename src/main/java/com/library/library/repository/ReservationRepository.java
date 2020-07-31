package com.library.library.repository;

import com.library.library.domain.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("select r from ReservationEntity r " +
            "order by r.id")
    List<ReservationEntity> getAll();
}
