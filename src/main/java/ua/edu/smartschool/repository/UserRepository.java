package ua.edu.smartschool.repository;

import java.util.Optional;
import ua.edu.smartschool.model.User;

/**
 * Інтерфейс репозиторію користувачів.
 * Визначає основні операції для роботи з користувачами.
 */
public interface UserRepository {

  /**
   * Повертає користувача за логіном.
   *
   * @param login логін користувача
   * @return Optional з користувачем або порожній Optional, якщо користувача не знайдено
   */
  Optional<User> findByLogin(String login);

  /**
   * Зберігає користувача у репозиторії.
   *
   * @param user користувач для збереження
   */
  void save(User user);

  /**
   * Очищає всі дані репозиторію.
   */
  void clear();
}