package com.library.library.repository;

import com.library.library.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select b from BookEntity b " +
            "order by b.title")
    List<BookEntity> getAll();
}
