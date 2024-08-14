package ru.practicum.shareit.booking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingCreateRequestDto;
import ru.practicum.shareit.booking.dto.BookingInfoResponseDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@Validated
@Slf4j
@RequiredArgsConstructor
public class BookingController {
    private static final String REQUEST_HEADER = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookingInfoResponseDto create(@RequestHeader(REQUEST_HEADER) final long userId,
                                  @Valid @RequestBody final BookingCreateRequestDto bookingCreateRequestDto) {
        log.info("Request POST  /bookings userId = {}, body = {}", userId, bookingCreateRequestDto);
        final BookingInfoResponseDto result = bookingService.create(userId, bookingCreateRequestDto);
        log.info("Response POST /bookings userId = {}, body = {}", userId, result);
        return result;
    }

    @PatchMapping("/{booking_id}")
    @ResponseStatus(HttpStatus.OK)
    BookingInfoResponseDto approve(@RequestHeader(REQUEST_HEADER) final long userId,
                                   @PathVariable("booking_id") final long bookingId,
                                   @RequestParam final boolean approved) {
        log.info("Request PATCH /bookings/{}?approved={}, userId = {}", bookingId, approved, userId);
        final BookingInfoResponseDto result = bookingService.approve(userId, bookingId, approved);
        log.info("Response PATCH /bookings/{}?approved={}, userId = {}, body = {}",
                bookingId, approved, userId, result);
        return result;
    }

    @GetMapping("/{booking_id}")
    @ResponseStatus(HttpStatus.OK)
    BookingInfoResponseDto getByBookingId(@RequestHeader(REQUEST_HEADER) final long userId,
                                          @PathVariable("booking_id") final long bookingId) {
        log.info("Request GET /bookings/{}, userId = {}", bookingId, userId);
        final BookingInfoResponseDto result = bookingService.getByBookingId(userId, bookingId);
        log.info("Response GET /bookings/{}, userId = {}, body = {}", bookingId, userId, result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<BookingInfoResponseDto> getBookings(@RequestHeader(REQUEST_HEADER) final long userId,
                                             @RequestParam(value = "state", defaultValue = "ALL") String bookingState,
                                             @RequestParam(value = "from", defaultValue = "0") Integer from,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("Request GET /bookings?state={}, userId = {}", bookingState, userId);
        final Pageable pageable = PageRequest.of(from / size, size);
        final List<BookingInfoResponseDto> result = bookingService.getBookings(userId, bookingState, pageable);
        log.info("Response GET /bookings?state={}, userId = {}, body = {}", bookingState, userId, result);
        return result;
    }

    @GetMapping("/owner")
    @ResponseStatus(HttpStatus.OK)
    List<BookingInfoResponseDto> getBookingsByOwner(@RequestHeader(REQUEST_HEADER) final long ownerId,
                                                    @RequestParam(value = "state", defaultValue = "ALL")
                                                    String bookingState,
                                                    @RequestParam(value = "from", defaultValue = "0") Integer from,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("Request GET /bookings/owner?state={}, ownerId = {}", bookingState, ownerId);
        final Pageable pageable = PageRequest.of(from / size, size);
        final List<BookingInfoResponseDto> result = bookingService.getBookingsByOwner(ownerId, bookingState, pageable);
        log.info("Response GET /bookings/owner?state={}, ownerId = {}, body = {}", bookingState, ownerId, result);
        return result;
    }
}