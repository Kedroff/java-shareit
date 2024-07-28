package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item persist(final Item item);

    Item update(final Item item);

    Optional<Item> get(final long itemId);

    List<Item> getAll(final long userId);

    List<Item> search(final String text);

    boolean isValidOwner(final Item item);
}
