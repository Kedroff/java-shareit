package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerIdOrderByStartDesc(long userId, Pageable pageable);

    List<Booking> findAllByBookerIdAndBookingStatusOrderByStartDesc(long userId, BookingStatus status,
                                                                    Pageable pageable);

    List<Booking> findAllByBookerIdAndStartAfterOrderByStartDesc(long userId, LocalDateTime localDateTime,
                                                                 Pageable pageable);

    List<Booking> findAllByBookerIdAndEndBeforeOrderByStartDesc(long userId, LocalDateTime localDateTime,
                                                                Pageable pageable);

    List<Booking> findAllByBookerIdAndItemIdAndBookingStatusAndEndBefore(long userId, long itemId,
                                                                         BookingStatus bookingStatus,
                                                                         LocalDateTime localDateTime,
                                                                         Pageable pageable);

    List<Booking> findAllByBookerIdAndStartBeforeAndEndAfterOrderByStartDesc(long userId,
                                                                             LocalDateTime start,
                                                                             LocalDateTime end,
                                                                             Pageable pageable);

    List<Booking> findAllByItemOwnerIdOrderByStartDesc(long ownerId, Pageable pageable);

    List<Booking> findAllByItemOwnerIdAndStartBeforeAndEndAfterOrderByStartDesc(long ownerId,
                                                                                LocalDateTime start,
                                                                                LocalDateTime end,
                                                                                Pageable pageable);

    List<Booking> findAllByItemOwnerIdAndEndBeforeOrderByStartDesc(long ownerId,
                                                                   LocalDateTime localDateTime,
                                                                   Pageable pageable);

    List<Booking> findAllByItemOwnerIdAndStartAfterOrderByStartDesc(long ownerId,
                                                                    LocalDateTime localDateTime,
                                                                    Pageable pageable);

    List<Booking> findAllByItemOwnerIdAndBookingStatusOrderByStartDesc(long ownerId, BookingStatus status,
                                                                       Pageable pageable);

    List<Booking> findByItemIdAndBookingStatus(long itemId, BookingStatus status);
}