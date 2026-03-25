package ua.edu.smartschool.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Клас, що представляє користувача системи SmartSchool. Містить основні дані користувача, такі як
 * логін, роль, хеш пароля, ПІБ та email.
 */
public class User {
  private static final AtomicLong SEQ = new AtomicLong(1);

  private final long id;
  private final String login;
  private final String passwordHash;
  private final Role role;
  private final String fullName;
  private final String email;

  /**
   * Створює нового користувача. Ідентифікатор генерується автоматично.
   *
   * @param login логін користувача
   * @param passwordHash хеш пароля користувача
   * @param role роль користувача
   * @param fullName повне ім'я користувача
   * @param email email користувача
   */
  public User(String login, String passwordHash, Role role, String fullName, String email) {
    this.id = SEQ.getAndIncrement();
    this.login = login;
    this.passwordHash = passwordHash;
    this.role = role;
    this.fullName = fullName;
    this.email = email;
  }

  /**
   * Повертає унікальний ідентифікатор користувача.
   *
   * @return id користувача
   */
  public long getId() {
    return id;
  }

  /**
   * Повертає логін користувача.
   *
   * @return логін користувача
   */
  public String getLogin() {
    return login;
  }

  /**
   * Повертає хеш пароля користувача.
   *
   * @return хеш пароля
   */
  public String getPasswordHash() {
    return passwordHash;
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
   * Повертає повне ім'я користувача.
   *
   * @return повне ім'я користувача
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Повертає email користувача.
   *
   * @return email користувача
   */
  public String getEmail() {
    return email;
  }
}
