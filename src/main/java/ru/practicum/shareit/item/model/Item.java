package ru.practicum.shareit.item.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "is_available", nullable = false)
    Boolean available;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    User owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @ToString.Exclude
    ItemRequest request;
}