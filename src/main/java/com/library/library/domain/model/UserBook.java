package com.library.library.domain.model;

import com.library.library.domain.entity.BookEntity;
import com.library.library.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBook {

    private Long id;

    private UserEntity user;

    private BookEntity book;

    private String status;
}
