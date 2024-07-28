package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequestDto {
    @NotBlank(message = "name cannot be null or blank")
    private String name;
    @NotNull(message = "email cannot be null")
    @Email(message = "email must be correct email")
    private String email;
}
