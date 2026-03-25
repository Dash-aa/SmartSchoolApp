package ua.edu.smartschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас запуску застосунку SmartSchool.
 * Ініціалізує та запускає Spring Boot додаток.
 */
@SpringBootApplication
public class SmartSchoolApplication {

  /**
   * Точка входу в програму.
   * Запускає Spring Boot застосунок.
   *
   * @param args аргументи командного рядка
   */
  public static void main(String[] args) {
    SpringApplication.run(SmartSchoolApplication.class, args);
  }
}