package ua.edu.smartschool.repository;

import org.springframework.stereotype.Repository;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> storage = new HashMap<>();

    public InMemoryUserRepository() {
        // seed user for tests/demo
        storage.put(
                "student1",
                new User(
                        "student1",
                        "Student!1234",
                        Role.STUDENT,
                        "Test Student",
                        "student1@school.ua"
                )
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(storage.get(login));
    }

    @Override
    public void save(User user) {
        storage.put(user.getLogin(), user);
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
