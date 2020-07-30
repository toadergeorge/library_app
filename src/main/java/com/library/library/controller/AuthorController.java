package com.library.library.controller;

import com.library.library.domain.model.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author) {
        return service.create(author);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody Author author) {
        author.setId(id);
        service.updateTransactional(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
