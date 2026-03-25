package ua.edu.smartschool.repository;

import java.util.Optional;
import ua.edu.smartschool.model.User;

public interface UserRepository {
  Optional<User> findByLogin(String login);

  void save(User user);

  void clear();
}
