package ua.edu.smartschool.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import ua.edu.smartschool.model.User;

@Repository
public class InMemoryUserRepository implements UserRepository {

  private final Map<String, User> storage = new ConcurrentHashMap<>();

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