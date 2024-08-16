package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserClient client;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid final UserDto userDto) {
        log.info("Post user");
        return client.createUser(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable final Long userId,
                                             @RequestBody @Valid final UserDto userDto) {

        log.info("PATCH update user; userId={}", userId);
        return client.updateUser(userId, userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable final Long userId) {

        log.info("GET user; userId={}", userId);
        return client.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delUser(@PathVariable final Long userId) {

        log.info("DELETE user; userId={}", userId);
        return client.delUser(userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                              @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        log.info("GET all users; from={}, size={}", from, size);
        return client.getAllUsers(from, size);
    }
}