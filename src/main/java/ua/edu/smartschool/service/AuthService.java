package ua.edu.smartschool.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.UserRepository;

/**
 * Сервіс авторизації та реєстрації користувачів. Виконує перевірку облікових даних під час входу та
 * створення нового користувача під час реєстрації. Паролі зберігаються у вигляді BCrypt-хешів
 * відповідно до нефункціональної вимоги R2.2.
 */
@Service
public class AuthService {

  private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Створює сервіс авторизації з вказаним репозиторієм користувачів та енкодером паролів.
   *
   * @param userRepository репозиторій для пошуку та збереження користувачів
   * @param passwordEncoder енкодер паролів на основі BCrypt
   */
  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Виконує авторизацію користувача за логіном і паролем. Перевіряє наявність користувача та
   * відповідність пароля з використанням BCrypt-перевірки.
   *
   * @param login логін користувача
   * @param password пароль користувача (у відкритому вигляді)
   * @return Optional з користувачем, якщо авторизація успішна, або порожній Optional, якщо дані
   *     невірні
   */
  public Optional<User> login(String login, String password) {
    logger.debug("Перевірка користувача {}", login);

    Optional<User> user =
        userRepository
            .findByLogin(login)
            .filter(u -> u.isActive())
            .filter(u -> passwordEncoder.matches(password, u.getPasswordHash()));

    if (user.isPresent()) {
      logger.info("Користувач {} успішно авторизований", login);
    } else {
      logger.warn("Невдала спроба входу для користувача {}", login);
    }

    return user;
  }

  /**
   * Реєструє нового користувача на основі даних форми. Виконує перевірку унікальності логіна, ролі
   * та коректності пароля. Пароль хешується BCrypt-енкодером перед збереженням у базу даних.
   *
   * @param form форма реєстрації користувача
   * @return Optional з текстом помилки, якщо реєстрація неможлива, або порожній Optional у випадку
   *     успішної реєстрації
   */
  public Optional<String> register(RegisterForm form) {
    logger.info("Початок реєстрації користувача {}", form.getLogin());

    String login = form.getLogin();
    String password = form.getPassword();
    String confirmPassword = form.getConfirmPassword();

    if (userRepository.existsByLogin(login)) {
      logger.warn("Спроба повторної реєстрації користувача {}", login);
      return Optional.of("Користувач із таким логіном уже існує");
    }

    if (form.getRole() == null) {
      logger.warn("Не вказано роль для користувача {}", login);
      return Optional.of("Необхідно обрати роль користувача");
    }

    if (password == null || confirmPassword == null) {
      logger.warn("Не вказано пароль для користувача {}", login);
      return Optional.of("Пароль і підтвердження пароля є обов'язковими");
    }

    if (!password.equals(confirmPassword)) {
      logger.warn("Паролі не збігаються для користувача {}", login);
      return Optional.of("Пароль і підтвердження пароля не збігаються");
    }

    String hashedPassword = passwordEncoder.encode(password);

    User user =
        new User(login, hashedPassword, form.getRole(), form.getFullName(), form.getEmail());

    userRepository.save(user);

    logger.info("Користувача {} успішно зареєстровано", login);
    return Optional.empty();
  }
}
