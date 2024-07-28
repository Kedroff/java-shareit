package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@Component
public class ItemMapperImpl implements ItemMapper {
    @Override
    public Item toModel(final long userId, final ItemCreateRequestDto itemCreateRequestDto) {
        return Item.builder()
                .name(itemCreateRequestDto.getName())
                .description(itemCreateRequestDto.getDescription())
                .available(itemCreateRequestDto.getAvailable())
                .owner(User.builder()
                        .id(userId)
                        .build())
                .build();
    }

    @Override
    public Item toModel(long userId, final long itemId, ItemUpdateRequestDto itemUpdateRequestDto) {
        return Item.builder()
                .id(itemId)
                .name(itemUpdateRequestDto.getName())
                .description(itemUpdateRequestDto.getDescription())
                .available(itemUpdateRequestDto.getAvailable())
                .owner(User.builder()
                        .id(userId)
                        .build())
                .build();
    }

    @Override
    public ItemInfoResponseDto toDto(final Item item) {
        return ItemInfoResponseDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }
}
