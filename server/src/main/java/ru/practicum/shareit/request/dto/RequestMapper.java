package ru.practicum.shareit.request.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.dto.model.RequestIncDto;
import ru.practicum.shareit.request.dto.model.RequestOutDto;
import ru.practicum.shareit.request.dto.model.RequestWithItemDto;
import ru.practicum.shareit.request.model.Request;

@Component
public class RequestMapper {
    public Request toRequestFromRequestIncDto(RequestIncDto request) {
        return new Request(null,
                request.getDescription(),
                null,
                null);
    }

    public RequestOutDto toRequestOutDtoFromRequest(Request request) {
        if (request == null) return null;
        return new RequestOutDto(request.getId(),
                request.getDescription(),
                request.getCreated());
    }

    public RequestWithItemDto toRequestWithItemDtoFromRequest(Request request) {
        if (request == null) return null;
        return new RequestWithItemDto(request.getId(),
                request.getDescription(),
                request.getCreated(),
                null);
    }
}
