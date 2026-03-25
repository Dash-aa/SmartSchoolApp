package ua.edu.smartschool.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.UserRepository;

/**
 * Сервіс авторизації та реєстрації користувачів.
 * Виконує перевірку облікових даних під час входу
 * та створення нового користувача під час реєстрації.
 */
@Service
public class AuthService {

  private final UserRepository userRepository;

  /**
   * Створює сервіс авторизації з вказаним репозиторієм користувачів.
   *
   * @param userRepository репозиторій для пошуку та збереження користувачів
   */
  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Виконує авторизацію користувача за логіном і паролем.
   * Перевіряє наявність користувача та відповідність пароля.
   *
   * @param login логін користувача
   * @param password пароль користувача
   * @return Optional з користувачем, якщо авторизація успішна,
   *         або порожній Optional, якщо дані невірні
   */
  public Optional<User> login(String login, String password) {
    return userRepository.findByLogin(login).filter(u -> u.getPasswordHash().equals(password));
  }

  /**
   * Реєструє нового користувача на основі даних форми.
   * Виконує перевірку унікальності логіна, ролі та коректності пароля.
   *
   * @param form форма реєстрації користувача
   * @return Optional з текстом помилки, якщо реєстрація неможлива,
   *         або порожній Optional у випадку успішної реєстрації
   */
  public Optional<String> register(RegisterForm form) {
    if (userRepository.findByLogin(form.getLogin()).isPresent()) {
      return Optional.of("Login already exists");
    }
    if (form.getRole() == null) {
      return Optional.of("Role is required");
    }
    if (form.getPassword() == null || form.getConfirmPassword() == null) {
      return Optional.of("Password is required");
    }
    if (!form.getPassword().equals(form.getConfirmPassword())) {
      return Optional.of("Passwords do not match");
    }

    userRepository.save(
            new User(
                    form.getLogin(),
                    form.getPassword(),
                    form.getRole(),
                    form.getFullName(),
                    form.getEmail()));

    userRepository.save(
            new User(
                    form.getLogin(),
                    form.getPassword(),
                    form.getRole(),
                    form.getFullName(),
                    form.getEmail()));
    return Optional.empty();
  }
}