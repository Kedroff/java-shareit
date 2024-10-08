package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemIncDto;

import java.util.Map;

@Service
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> createItem(final ItemIncDto itemDto,
                                             final Long userId) {

        return post("", userId, itemDto);
    }

    public ResponseEntity<Object> updateItem(final Long itemId,
                                             final ItemIncDto itemDto,
                                             final Long userId) {

        return patch("/" + itemId, userId, itemDto);
    }

    public ResponseEntity<Object> getItem(final Long userId,
                                          final Long itemId) {

        return get("/" + itemId, userId);
    }

    public ResponseEntity<Object> getItemsUser(final Long userId,
                                               final Integer from,
                                               final Integer size) {

        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("?from={from}&size={size}", userId, parameters);
    }

    public ResponseEntity<Object> searchItems(final String text) {

        Map<String, Object> parameters = Map.of("text", text);
        return get("/search?text={text}", null, parameters);
    }

    public ResponseEntity<Object> addComment(final Long userId,
                                             final CommentDto comment,
                                             final Long itemId) {

        return post("/" + itemId + "/comment", userId, comment);
    }
}