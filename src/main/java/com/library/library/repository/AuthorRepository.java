package com.library.library.repository;

import com.library.library.domain.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("select a from AuthorEntity a " +
            "order by a.firstName")
    List<AuthorEntity> getAll();
}
