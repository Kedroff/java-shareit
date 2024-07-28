package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;

import java.util.List;

public interface ItemService {
    ItemInfoResponseDto create(final long userId, final ItemCreateRequestDto itemCreateRequestDto);

    ItemInfoResponseDto update(final long userId, final long itemId, final ItemUpdateRequestDto itemUpdateRequestDto);

    ItemInfoResponseDto get(final long userId, final long itemId);

    List<ItemInfoResponseDto> getAll(final long userId);

    List<ItemInfoResponseDto> search(final String text);
}
