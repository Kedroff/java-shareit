package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final HashSet<String> userEmails = new HashSet<>();
    private final HashMap<Long, User> userStorage = new HashMap<>();
    private Long seq = 0L;

    @Override
    public User persist(final User user) {
        user.setId(getSeq());
        userStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(final User user) {
        userStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean removeEmail(String email) {
        return userEmails.remove(email);
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return !userEmails.contains(email);
    }

    @Override
    public boolean saveEmail(String email) {
        return userEmails.add(email);
    }

    @Override
    public Optional<User> get(final long userId) {
        final User original = userStorage.get(userId);
        return Optional.ofNullable(original);
    }

    @Override
    public List<User> getAll() {
        return userStorage.values().stream()
                .toList();
    }

    @Override
    public void delete(final long userId) {
        userStorage.remove(userId);
    }

    private Long getSeq() {
        return ++seq;
    }

}
