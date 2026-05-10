package ua.edu.smartschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфігурація безпеки. Реєструє BCryptPasswordEncoder як bean у контексті Spring, щоб його можна
 * було впроваджувати у сервіси для хешування та перевірки паролів.
 */
@Configuration
public class SecurityConfig {

  /**
   * Створює та повертає BCrypt-енкодер для хешування паролів. Алгоритм BCrypt автоматично генерує
   * випадкову сіль для кожного пароля і забезпечує захист від атак шляхом підбору.
   *
   * @return екземпляр PasswordEncoder на основі BCrypt
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
