package ua.edu.smartschool.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;

/**
 * Репозиторій користувачів. Розширює JpaRepository, що автоматично надає стандартні CRUD-операції
 * (save, findById, findAll, delete тощо). Власні методи запитів реалізуються Spring Data JPA на
 * основі найменування методу.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Шукає користувача за логіном.
   *
   * @param login логін користувача
   * @return Optional з користувачем або порожній Optional, якщо користувача не знайдено
   */
  Optional<User> findByLogin(String login);

  /**
   * Перевіряє, чи існує користувач з вказаним логіном.
   *
   * @param login логін для перевірки
   * @return true, якщо користувач з таким логіном існує
   */
  boolean existsByLogin(String login);

  /**
   * Повертає список користувачів за заданою роллю.
   *
   * @param role роль користувачів
   * @return список користувачів з вказаною роллю
   */
  List<User> findByRole(Role role);

  /**
   * Повертає список активних користувачів.
   *
   * @return список користувачів зі статусом is_active = true
   */
  List<User> findByIsActiveTrue();
}
