package com.library.library.service;

import com.library.library.domain.entity.ReservationEntity;
import com.library.library.domain.model.Book;
import com.library.library.domain.model.Reservation;
import com.library.library.domain.model.User;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.BookEntityToBookMapper;
import com.library.library.mapper.ReservationEntityToReservationMapper;
import com.library.library.mapper.ReservationToReservationEntityMapper;
import com.library.library.mapper.UserToUserEntityMapper;
import com.library.library.repository.BookRepository;
import com.library.library.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;
    private final BookRepository bookRepository;

    private final ReservationEntityToReservationMapper reservationEntityToReservationMapper;
    private final ReservationToReservationEntityMapper reservationToReservationEntityMapper;
    private final UserToUserEntityMapper userToUserEntityMapper;
    private final BookEntityToBookMapper bookEntityToBookMapper;

    private final UserService userService;

    public Reservation findById(long reservationId) {
        return repository.findById(reservationId)
                .map(reservationEntityToReservationMapper::convert)
                .orElseThrow(() -> new AuthorNotFoundException("The user with id provided cannot be found"));
    }

    public List<Reservation> getAll() {
        return repository.getAll()
                .stream()
                .map(reservationEntityToReservationMapper::convert)
                .collect(Collectors.toList());
    }

    public Reservation create(Reservation reservation) {
        ReservationEntity reservationEntity = reservationToReservationEntityMapper.convert(reservation);

        ReservationEntity savedEntity = repository.save(reservationEntity);
        return reservationEntityToReservationMapper.convert(savedEntity);
    }

    @Transactional
    public void createUserReservation(Long userId, Long reservationId, List<Book> userBooks) {

        User user = userService.findById(userId);
        Reservation reservation = findById(reservationId);

        for (Book book : userBooks) {
            if (book.getId() != null) {
                Book dbBook = bookRepository.findById(book.getId())
                        .map(bookEntityToBookMapper::convert)
                        .orElse(null);

                createReservationItem(dbBook, reservation);

            } else {
                //Book newBook = bookService.create(book);
                //userBookService.create(newBook, user);
            }
        }
    }

    public Reservation createReservationItem(Book dbBook, Reservation reservation) {
        ReservationEntity reservationEntity = reservationToReservationEntityMapper.convert(reservation);

        ReservationEntity savedEntity = repository.save(reservationEntity);
        return reservationEntityToReservationMapper.convert(savedEntity);
    }

    @Transactional
    public void updateTransactional(Reservation reservation) {
        ReservationEntity existingEntity = repository.findById(reservation.getId())
                .orElseThrow(() -> new AuthorNotFoundException("The reservation with id provided cannot be found"));

        updateFields(existingEntity, reservation);
    }

    private void updateFields(ReservationEntity existingEntity, Reservation reservation) {
        existingEntity.setCreatedAt(reservation.getCreatedAt());
        existingEntity.setDeliveryAddress(reservation.getDeliveryAddress());
        existingEntity.setStatus(reservation.getStatus());
        existingEntity.setUpdatedAt(reservation.getUpdatedAt());
        existingEntity.setUser(userToUserEntityMapper.convert(reservation.getUser()));
    }

    public void delete(Long id) {
        ReservationEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("The reservation with id provided cannot be found"));

        repository.delete(existingEntity);
    }
}
