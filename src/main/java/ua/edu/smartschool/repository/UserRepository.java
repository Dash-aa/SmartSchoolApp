package ua.edu.smartschool.repository;

import ua.edu.smartschool.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);
    void save(User user);
    void clear();
}
