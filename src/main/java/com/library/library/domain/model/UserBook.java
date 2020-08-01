package com.library.library.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBook {

    private Long id;

    private User user;

    private Book book;

    private String status;
}
