package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.model.User;

public interface UserMapper {
    User toModel(final UserCreateRequestDto userCreateRequestDto);

    User toModel(final UserUpdateRequestDto userUpdateRequestDto, final long userId);

    UserInfoResponseDto toDto(final User user);
}
