package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.annotations.UserControllerExceptionHandler;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.ResponseToUserDeletion;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@UserControllerExceptionHandler
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody final UserDto userDto) {
        log.info("Create user");
        return userService.createUser(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@PathVariable final Long userId,
                              @RequestBody final UserDto userDto) {

        log.info("PATCH update user; userId={}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable final Long userId) {

        log.info("Get user; userId={}", userId);
        return userService.getUser(userId);

    }

    @DeleteMapping("/{userId}")
    public ResponseToUserDeletion delUser(@PathVariable final Long userId) {

        log.info("Delete user; userId={}", userId);
        return userService.delUser(userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam final Integer from,
                                     @RequestParam final Integer size) {

        log.info("Get all users; from={}, size={}", from, size);
        return userService.getAllUsers(from, size);
    }
}
