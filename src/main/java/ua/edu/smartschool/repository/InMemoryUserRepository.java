package ua.edu.smartschool.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;

/**
 * Реалізація репозиторію користувачів у пам'яті. Використовується для зберігання користувачів без
 * підключення до бази даних.
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

  private final Map<String, User> storage = new HashMap<>();

  /** Створює репозиторій користувачів і додає тестового користувача. */
  public InMemoryUserRepository() {
    // seed user for tests/demo
    storage.put(
        "student1",
        new User("student1", "Student!1234", Role.STUDENT, "Test Student", "student1@school.ua"));
  }

  /**
   * Повертає користувача за логіном.
   *
   * @param login логін користувача
   * @return Optional з користувачем або порожній Optional, якщо не знайдено
   */
  @Override
  public Optional<User> findByLogin(String login) {
    return Optional.ofNullable(storage.get(login));
  }

  /**
   * Зберігає користувача у репозиторії.
   *
   * @param user користувач для збереження
   */
  @Override
  public void save(User user) {
    storage.put(user.getLogin(), user);
  }

  /** Очищає всі дані у репозиторії. */
  @Override
  public void clear() {
    storage.clear();
  }
}
