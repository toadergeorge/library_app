package com.library.library.controller;

import com.library.library.domain.model.Reservation;
import com.library.library.exception.ReservationNotFoundException;
import com.library.library.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getOneReservation(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservation() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody Reservation reservation) {
        return service.create(reservation);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        service.updateTransactional(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
