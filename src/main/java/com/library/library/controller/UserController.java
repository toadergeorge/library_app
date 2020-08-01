package com.library.library.controller;

import com.library.library.domain.model.Book;
import com.library.library.domain.model.User;
import com.library.library.exception.UserNotFoundException;
import com.library.library.service.ReservationService;
import com.library.library.service.UserBookService;
import com.library.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final UserBookService userBookService;
    private final ReservationService reservationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getOneUser(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @PostMapping("/{userId}/Books/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUserBook(@PathVariable("userId") long userId, @RequestBody List<Book> userBooks) {
        service.createUserBook(userId, userBooks);
    }

    @PostMapping("/{userId}/Reservation/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUserReservation(@PathVariable("userId") long userId, @PathVariable("reservationId") long reservationId, @RequestBody List<Book> userBooks) {
        reservationService.createUserReservation(userId, reservationId, userBooks);
    }

    @DeleteMapping("/{userId}/Books/{userBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserBook(@PathVariable("userBookId") long userBookId) {
        userBookService.delete(userBookId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody User user) {
        user.setId(id);
        service.updateTransactional(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
