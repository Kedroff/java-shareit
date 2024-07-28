package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ExceptionUtils;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;

    @Override
    public ItemInfoResponseDto create(final long userId, final ItemCreateRequestDto itemCreateRequestDto) {
        checkOwner(userId);
        final Item newItem = itemMapper.toModel(userId, itemCreateRequestDto);
        final Item saved = itemRepository.persist(newItem);
        return itemMapper.toDto(saved);
    }

    @Override
    public ItemInfoResponseDto update(final long userId, final long itemId,
                                      final ItemUpdateRequestDto itemUpdateRequestDto) {
        checkOwner(userId);
        final Item original = itemRepository.get(itemId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(itemId));
        final Item newItem = itemMapper.toModel(userId, itemId, itemUpdateRequestDto);
        checkAccess(newItem);
        updateEntityIfCorrect(original, newItem);
        return itemMapper.toDto(itemRepository.update(original));

    }

    private void updateEntityIfCorrect(final Item original, final Item newItem) {
        updateNameIfCorrect(original, newItem);
        updateDescriptionIfCorrect(original, newItem);
        updateAvailableIfCorrect(original, newItem);
    }

    private void updateNameIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getName()) && !original.getName().equals(newItem.getName())) {
            original.setName(newItem.getName());
        }
    }

    private void updateDescriptionIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getDescription()) && !original.getDescription().equals(newItem.getDescription())) {
            original.setDescription(newItem.getDescription());
        }
    }

    private void updateAvailableIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getAvailable()) && !original.getAvailable() == newItem.getAvailable()) {
            original.setAvailable(newItem.getAvailable());
        }
    }

    @Override
    public ItemInfoResponseDto get(final long userId, final long itemId) {
        checkOwner(userId);
        final Item original = itemRepository.get(itemId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(itemId)
        );
        return itemMapper.toDto(original);
    }

    @Override
    public List<ItemInfoResponseDto> getAll(final long userId) {
        checkOwner(userId);
        return itemRepository.getAll(userId).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemInfoResponseDto> search(String text) {
        if (Objects.isNull(text) || text.isBlank()) {
            return List.of();
        }
        return itemRepository.search(text.toLowerCase()).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    private void checkOwner(final long userId) {
        userRepository.get(userId)
                .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId));
    }

    private void checkAccess(final Item item) {
        if (!itemRepository.isValidOwner(item)) {
            throw new ValidationException(
                    String.format("Forbidden! User = %s don't have permission to access item = %s",
                            item.getOwner().getId(), item.getId()));
        }
    }

}
