package ua.edu.smartschool.repository;

import ua.edu.smartschool.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository {

    private final Map<String, User> byLogin = new ConcurrentHashMap<>();

    public Optional<User> findByLogin(String login) {
        if (login == null) return Optional.empty();
        return Optional.ofNullable(byLogin.get(login));
    }

    public boolean existsByLogin(String login) {
        return login != null && byLogin.containsKey(login);
    }

    public User save(User user) {
        byLogin.put(user.getLogin(), user);
        return user;
    }

    public int count() {
        return byLogin.size();
    }
}
