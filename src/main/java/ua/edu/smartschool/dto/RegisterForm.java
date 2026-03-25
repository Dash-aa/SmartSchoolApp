package ua.edu.smartschool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ua.edu.smartschool.model.Role;

/**
 * DTO-клас для зберігання даних форми реєстрації користувача.
 * Містить логін, пароль, повтор пароля, роль, ПІБ та email.
 */
public class RegisterForm {

  @NotBlank(message = "Логін є обов'язковим")
  @Size(min = 3, max = 32, message = "Логін має бути від 3 до 32 символів")
  @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Логін може містити лише латиницю, цифри та _")
  private String login;

  @NotBlank(message = "Пароль є обов'язковим")
  @Size(min = 8, max = 64, message = "Пароль має бути від 8 до 64 символів")
  @Pattern(
          regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
          message = "Пароль має містити велику/малу літеру, цифру та спецсимвол")
  private String password;

  @NotBlank(message = "Повтор паролю є обов'язковим")
  private String confirmPassword;

  @NotNull(message = "Роль є обов'язковою")
  private Role role;

  @NotBlank(message = "ПІБ є обов'язковим")
  @Size(min = 3, max = 80, message = "ПІБ має бути від 3 до 80 символів")
  private String fullName;

  @Email(message = "Некоректний email")
  private String email;

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

  /**
   * Повертає підтвердження пароля.
   *
   * @return підтвердження пароля
   */
  public String getConfirmPassword() {
    return confirmPassword;
  }

  /**
   * Встановлює підтвердження пароля.
   *
   * @param confirmPassword підтвердження пароля
   */
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  /**
   * Повертає роль користувача.
   *
   * @return роль користувача
   */
  public Role getRole() {
    return role;
  }

  /**
   * Встановлює роль користувача.
   *
   * @param role роль користувача
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Повертає повне ім'я користувача.
   *
   * @return повне ім'я користувача
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Встановлює повне ім'я користувача.
   *
   * @param fullName повне ім'я користувача
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Повертає email користувача.
   *
   * @return email користувача
   */
  public String getEmail() {
    return email;
  }

  /**
   * Встановлює email користувача.
   *
   * @param email email користувача
   */
  public void setEmail(String email) {
    this.email = email;
  }
}