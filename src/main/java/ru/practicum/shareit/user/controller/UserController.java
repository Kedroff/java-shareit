package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto create(@RequestBody @Valid final UserCreateRequestDto userCreateRequestDto) {
        log.info("request POST /users body = {}", userCreateRequestDto);
        final UserInfoResponseDto result = userService.create(userCreateRequestDto);
        log.info("response POST /users body = {}", result);
        return result;
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto update(@RequestBody @Valid final UserUpdateRequestDto userUpdateRequestDto,
                                      @PathVariable("userId") final long userId) {
        log.info("request PATH /users/{} body = {}", userId, userUpdateRequestDto);
        final UserInfoResponseDto result = userService.update(userUpdateRequestDto, userId);
        log.info("response PATH /users/{} body = {}", userId, result);
        return result;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto get(@PathVariable("userId") final long userId) {
        log.info("request GET /users/{}", userId);
        final UserInfoResponseDto result = userService.get(userId);
        log.info("response GET /users/{} body = {}", userId, result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoResponseDto> getAll() {
        log.info("request GET /users");
        final List<UserInfoResponseDto> result = userService.getAll();
        log.info("response GET /users body = {}", result);
        return result;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("userId") final long userId) {
        log.info("request DELETE /users/{}", userId);
        userService.delete(userId);
        log.info("response DELETE /users/{}", userId);
    }
}