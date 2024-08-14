package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.CommentAddRequestDto;
import ru.practicum.shareit.item.dto.CommentInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemFullInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private static final String REQUEST_HEADER = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto createItem(@RequestHeader(REQUEST_HEADER) final long userid,
                                          @RequestBody @Valid final ItemCreateRequestDto itemCreateRequestDto) {
        log.info("request POST /items userId = {}, body = {}", userid, itemCreateRequestDto);
        final ItemInfoResponseDto result = itemService.create(userid, itemCreateRequestDto);
        log.info("response POST /items userId = {}, body = {}", userid, result);
        return result;
    }

    @PatchMapping("/{item_id}")
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto updateItem(@RequestHeader(REQUEST_HEADER) final long userId,
                                          @PathVariable("item_id") final long itemId,
                                          @RequestBody @Valid final ItemUpdateRequestDto itemUpdateRequestDto) {
        log.info("request PATCH /items/{} userId = {}, body = {}", itemId, userId, itemUpdateRequestDto);
        final ItemInfoResponseDto result = itemService.update(userId, itemId, itemUpdateRequestDto);
        log.info("response PATCH /items{} userId = {},body = {}", itemId, userId, result);
        return result;
    }

    @GetMapping("/{item_id}")
    @ResponseStatus(HttpStatus.OK)
    public ItemFullInfoResponseDto get(@PathVariable("item_id") final long itemId) {
        log.info("request GET /items/{} ", itemId);
        final ItemFullInfoResponseDto result = itemService.get(itemId);
        log.info("response GET /items/ body ={}", result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemInfoResponseDto> getAll(@RequestHeader(REQUEST_HEADER) final long userid,
                                            @RequestParam(value = "from", defaultValue = "0") final int from,
                                            @RequestParam(value = "size", defaultValue = "10") final int size) {
        log.info("request GET /items/ userId = {}", userid);
        final Pageable pageable = PageRequest.of(from / size, size);
        final List<ItemInfoResponseDto> result = itemService.getAll(userid, pageable);
        log.info("response GET /items/ userId= {}, body = {}", userid, result);
        return result;
    }

    @GetMapping("/search")
    public List<ItemInfoResponseDto> search(@RequestParam(name = "text") final String text,
                                            @RequestParam(value = "from", defaultValue = "0") final int from,
                                            @RequestParam(value = "size", defaultValue = "10") final int size) {
        log.info("request GET /items/search?text={}", text);
        final Pageable pageable = PageRequest.of(from / size, size);
        List<ItemInfoResponseDto> result = itemService.search(text, pageable);
        log.info("response GET /items/search?text={}, body = {}", text, result);
        return result;
    }

    @PostMapping("/{item_id}/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentInfoResponseDto addComment(@RequestHeader(REQUEST_HEADER) final long userId,
                                             @PathVariable("item_id") final long itemId,
                                             @RequestBody @Valid final CommentAddRequestDto commentAddRequestDto) {
        log.info("request POST /items/{}/comment userId ={}, body = {}", itemId, userId, commentAddRequestDto);
        final CommentInfoResponseDto result = itemService.addComment(userId, itemId, commentAddRequestDto);
        log.info("response POST /items/{}/comment userId = {}, body = {}", userId, itemId, result);
        return result;
    }
}