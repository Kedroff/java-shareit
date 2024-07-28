package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User persist(final User user);

    User update(final User user);

    Optional<User> get(final long userId);

    List<User> getAll();

    void delete(final long userId);

    boolean removeEmail(String email);

    boolean saveEmail(String email);

    boolean isUniqueEmail(String email);
}
