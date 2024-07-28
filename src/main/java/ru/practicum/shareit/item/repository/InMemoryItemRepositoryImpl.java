package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InMemoryItemRepositoryImpl implements ItemRepository {
    private final HashMap<Long, Item> itemStorage = new HashMap<>();
    private final HashMap<Long, List<Item>> itemsByOwnerStorage = new HashMap<>();
    private Long seq = 0L;

    @Override
    public Item persist(final Item item) {
        item.setId(getSeq());
        itemStorage.put(item.getId(), item);
        itemsByOwnerStorage.computeIfAbsent(item.getOwner().getId(), k -> new ArrayList<>()).add(item);
        return item;
    }

    @Override
    public Item update(final Item item) {
        itemStorage.put(item.getId(), item);
        List<Item> items = itemsByOwnerStorage.get(item.getOwner().getId());
        items.remove(item);
        items.add(item);
        return item;
    }

    @Override
    public boolean isValidOwner(final Item item) {
        return itemsByOwnerStorage.getOrDefault(item.getOwner().getId(), new ArrayList<>()).contains(item);
    }

    @Override
    public Optional<Item> get(final long itemId) {
        return Optional.ofNullable(itemStorage.get(itemId));
    }

    @Override
    public List<Item> getAll(final long userId) {
        return itemsByOwnerStorage.getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public List<Item> search(String text) {
        return itemStorage.values().stream()
                .filter(item -> (item.getName().toLowerCase().contains(text)) ||
                        item.getDescription().toLowerCase().contains(text)
                                && item.getAvailable())
                .collect(Collectors.toList());
    }

    private Long getSeq() {
        return ++seq;
    }
}
