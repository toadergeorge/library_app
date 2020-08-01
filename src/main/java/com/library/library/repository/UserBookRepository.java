package com.library.library.repository;

import com.library.library.domain.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRepository extends JpaRepository<UserBookEntity, Long> {
}
