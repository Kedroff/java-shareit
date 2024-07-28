package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.model.User;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toModel(final UserCreateRequestDto userCreateRequestDto) {
        return User.builder()
                .name(userCreateRequestDto.getName())
                .email(userCreateRequestDto.getEmail())
                .build();
    }

    @Override
    public User toModel(UserUpdateRequestDto userUpdateRequestDto, long userId) {
        return User.builder()
                .id(userId)
                .name(userUpdateRequestDto.getName())
                .email(userUpdateRequestDto.getEmail())
                .build();
    }

    @Override
    public UserInfoResponseDto toDto(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
