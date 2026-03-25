package ua.edu.smartschool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO-клас для зберігання даних форми входу користувача.
 * Містить логін та пароль, які вводяться у формі авторизації.
 */
public class LoginForm {

  @NotBlank(message = "Логін є обов'язковим")
  @Size(min = 3, max = 32, message = "Логін має бути від 3 до 32 символів")
  @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Логін може містити лише латиницю, цифри та _")
  private String login;

  @NotBlank(message = "Пароль є обов'язковим")
  @Size(min = 6, max = 64, message = "Пароль має бути від 6 до 64 символів")
  private String password;

  /**
   * Повертає логін користувача.
   *
   * @return логін користувача
   */
  public String getLogin() {
    return login;
  }

  /**
   * Встановлює логін користувача.
   *
   * @param login логін користувача
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * Повертає пароль користувача.
   *
   * @return пароль користувача
   */
  public String getPassword() {
    return password;
  }

  /**
   * Встановлює пароль користувача.
   *
   * @param password пароль користувача
   */
  public void setPassword(String password) {
    this.password = password;
  }
}