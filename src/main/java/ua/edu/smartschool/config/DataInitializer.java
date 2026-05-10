package ua.edu.smartschool.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.UserRepository;

/**
 * Ініціалізація демонстраційних даних при старті застосунку. Додає тестових користувачів усіх ролей
 * до бази даних, якщо їх ще немає. Паролі демо-користувачів хешуються BCrypt-енкодером перед
 * збереженням.
 */
@Configuration
public class DataInitializer {

  private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

  /**
   * Створює та реєструє у Spring CommandLineRunner для ініціалізації демонстраційних користувачів.
   *
   * @param userRepository репозиторій користувачів
   * @param passwordEncoder енкодер паролів BCrypt
   * @return runner, що виконує ініціалізацію демо-користувачів
   */
  @Bean
  public CommandLineRunner initDemoUsers(
      UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      logger.info("Ініціалізація демонстраційних користувачів...");

      createIfMissing(
          userRepository,
          passwordEncoder,
          "admin",
          "Admin!1234",
          Role.ADMIN,
          "Адміністратор Системи",
          "admin@school.ua");

      createIfMissing(
          userRepository,
          passwordEncoder,
          "teacher_math",
          "Teacher!1234",
          Role.TEACHER,
          "Іваненко Марія Петрівна",
          "ivanenko@school.ua");

      createIfMissing(
          userRepository,
          passwordEncoder,
          "student1",
          "Student!1234",
          Role.STUDENT,
          "Петренко Олександр Іванович",
          "petrenko@school.ua");

      logger.info("Ініціалізація демонстраційних користувачів завершена");
    };
  }

  /**
   * Створює користувача в базі даних, якщо користувач з таким логіном ще не існує. Пароль
   * автоматично хешується BCrypt-енкодером.
   */
  private void createIfMissing(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      String login,
      String rawPassword,
      Role role,
      String fullName,
      String email) {
    if (userRepository.findByLogin(login).isEmpty()) {
      String hashedPassword = passwordEncoder.encode(rawPassword);
      User user = new User(login, hashedPassword, role, fullName, email);
      userRepository.save(user);
      logger.info("Створено демо-користувача: {} ({})", login, role);
    } else {
      logger.debug("Користувач {} вже існує, пропускаємо створення", login);
    }
  }
}
