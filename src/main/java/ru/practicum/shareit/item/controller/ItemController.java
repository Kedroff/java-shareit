package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ItemInfoResponseDto createItem(@RequestHeader(REQUEST_HEADER) final long userid,
                                          @RequestBody @Valid final ItemCreateRequestDto itemCreateRequestDto) {
        log.info("request POST /items userId = {}, body = {}", userid, itemCreateRequestDto);
        final ItemInfoResponseDto result = itemService.create(userid, itemCreateRequestDto);
        log.info("response POST /items userId = {}, body = {}", userid, result);
        return result;
    }

    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto updateItem(@RequestHeader(REQUEST_HEADER) final long userId,
                                          @PathVariable final long itemId,
                                          @RequestBody @Valid final ItemUpdateRequestDto itemUpdateRequestDto) {
        log.info("request PATCH /items/{} userId = {}, body = {}", itemId, userId, itemUpdateRequestDto);
        final ItemInfoResponseDto result = itemService.update(userId, itemId, itemUpdateRequestDto);
        log.info("response PATCH /items{} userId = {},body = {}", itemId, userId, result);
        return result;
    }

    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto get(@RequestHeader(REQUEST_HEADER) final long userid,
                                   @PathVariable final long itemId) {
        log.info("request GET /items/{} userId = {}", itemId, userid);
        final ItemInfoResponseDto result = itemService.get(userid, itemId);
        log.info("response GET /items/{} body ={}", userid, result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemInfoResponseDto> getAll(@RequestHeader(REQUEST_HEADER) final long userid) {
        log.info("request GET /items/ userId = {}", userid);
        final List<ItemInfoResponseDto> result = itemService.getAll(userid);
        log.info("response GET /items/ userId= {}, body = {}", userid, result);
        return result;
    }

    @GetMapping("/search")
    public List<ItemInfoResponseDto> search(@RequestParam(name = "text") final String text) {
        log.info("request GET /items/search?text={}", text);
        List<ItemInfoResponseDto> result = itemService.search(text);
        log.info("response GET /items/search?text={}, body = {}", text, result);
        return result;
    }
}
