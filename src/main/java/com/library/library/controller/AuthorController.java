package com.library.library.controller;

import com.library.library.domain.model.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getOneAuthor(@PathVariable("id") long id) {

        return service.findById(id);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
