package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;
import ru.practicum.shareit.item.model.Item;

public interface ItemMapper {
    Item toModel(final long userId, final ItemCreateRequestDto itemCreateRequestDto);

    Item toModel(final long userId, final long itemId, final ItemUpdateRequestDto itemUpdateRequestDto);

    ItemInfoResponseDto toDto(final Item item);
}
