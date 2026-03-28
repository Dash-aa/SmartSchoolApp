package ua.edu.smartschool;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас застосунку SmartSchool
 * Виконує запуск і завершення програми
 */
@SpringBootApplication
public class SmartSchoolApplication {

  // Логер для відстеження запуску і завершення програми
  private static final Logger logger = LoggerFactory.getLogger(SmartSchoolApplication.class);

  public static void main(String[] args) {
    // Логування запуску застосунку
    logger.info("Запуск застосунку SmartSchool");

    SpringApplication.run(SmartSchoolApplication.class, args);
  }

  @PreDestroy
  public void onShutdown() {
    // Логування завершення роботи
    logger.info("Завершення роботи застосунку SmartSchool");
  }
}