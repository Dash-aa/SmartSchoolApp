package ua.edu.smartschool.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Сервіс авторизації та реєстрації користувачів. Виконує перевірку облікових даних під час входу та
 * створення нового користувача під час реєстрації.
 */
@Service
public class AuthService {

  private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

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
   * Виконує авторизацію користувача за логіном і паролем. Перевіряє наявність користувача та
   * відповідність пароля.
   *
   * @param login логін користувача
   * @param password пароль користувача
   * @return Optional з користувачем, якщо авторизація успішна, або порожній Optional, якщо дані
   *     невірні
   */
  public Optional<User> login(String login, String password) {
    logger.debug("Перевірка користувача {}", login);

    Optional<User> user =
            userRepository.findByLogin(login).filter(u -> u.getPasswordHash().equals(password));

    if (user.isPresent()) {
      logger.info("Користувач {} успішно авторизований", login);
    } else {
      logger.warn("Невдала спроба входу для користувача {}", login);
    }

    return user;
  }

  /**
   * Реєструє нового користувача на основі даних форми. Виконує перевірку унікальності логіна, ролі
   * та коректності пароля.
   *
   * @param form форма реєстрації користувача
   * @return Optional з текстом помилки, якщо реєстрація неможлива, або порожній Optional у випадку
   *     успішної реєстрації
   */
  public Optional<String> register(RegisterForm form) {
    logger.info("Початок реєстрації користувача {}", form.getLogin());

    if (userRepository.findByLogin(form.getLogin()).isPresent()) {
      logger.warn("Спроба повторної реєстрації користувача {}", form.getLogin());
      return Optional.of("Користувач із таким логіном уже існує");
    }

    if (form.getRole() == null) {
      logger.warn("Не вказано роль для користувача {}", form.getLogin());
      return Optional.of("Роль є обов’язковою");
    }

    if (form.getPassword() == null || form.getConfirmPassword() == null) {
      logger.warn("Не вказано пароль для користувача {}", form.getLogin());
      return Optional.of("Пароль є обов’язковим");
    }

    if (!form.getPassword().equals(form.getConfirmPassword())) {
      logger.warn("Паролі не збігаються для користувача {}", form.getLogin());
      return Optional.of("Паролі не збігаються");
    }

    userRepository.save(
            new User(
                    form.getLogin(),
                    form.getPassword(),
                    form.getRole(),
                    form.getFullName(),
                    form.getEmail()));

    logger.info("Користувача {} успішно зареєстровано", form.getLogin());
    return Optional.empty();
  }
}