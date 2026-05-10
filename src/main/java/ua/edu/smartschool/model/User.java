package ua.edu.smartschool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Клас, що представляє користувача системи SmartSchool. Містить основні дані користувача, такі як
 * логін, роль, хеш пароля, ПІБ та email. Зберігається в таблиці users реляційної бази даних.
 */
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "login", nullable = false, unique = true, length = 50)
  private String login;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false, length = 20)
  private Role role;

  @Column(name = "full_name", length = 100)
  private String fullName;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  /** Конструктор без параметрів, необхідний для JPA. */
  protected User() {}

  /**
   * Створює нового користувача. Ідентифікатор генерується автоматично базою даних.
   *
   * @param login логін користувача
   * @param passwordHash хеш пароля користувача
   * @param role роль користувача
   * @param fullName повне ім'я користувача
   * @param email email користувача
   */
  public User(String login, String passwordHash, Role role, String fullName, String email) {
    this.login = login;
    this.passwordHash = passwordHash;
    this.role = role;
    this.fullName = fullName;
    this.email = email;
    this.isActive = true;
  }

  /**
   * Повертає унікальний ідентифікатор користувача.
   *
   * @return id користувача
   */
  public Long getId() {
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
   * Встановлює новий хеш пароля користувача.
   *
   * @param passwordHash новий хеш пароля
   */
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
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
   * Встановлює нове повне ім'я користувача.
   *
   * @param fullName нове повне ім'я
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
   * Встановлює новий email користувача.
   *
   * @param email новий email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Перевіряє, чи активний обліковий запис користувача.
   *
   * @return true, якщо обліковий запис активний
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Встановлює статус активності облікового запису.
   *
   * @param active новий статус активності
   */
  public void setActive(boolean active) {
    this.isActive = active;
  }
}
