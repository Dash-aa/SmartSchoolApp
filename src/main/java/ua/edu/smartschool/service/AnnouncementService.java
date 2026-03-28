package ua.edu.smartschool.service;

import java.util.List;
import ua.edu.smartschool.model.Announcement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Сервіс для роботи з оголошеннями. Надає список актуальних оголошень для відображення у
 * застосунку.
 */
public class AnnouncementService {

  private static final Logger logger = LoggerFactory.getLogger(AnnouncementService.class);

  /**
   * Повертає список актуальних оголошень.
   *
   * @return список оголошень
   */
  public List<Announcement> getActualAnnouncements() {
    logger.info("Отримання списку актуальних оголошень");
    return List.of(
        new Announcement("Збори", "Збори учнів о 15:00"),
        new Announcement("Контрольна", "Контрольна з математики у пʼятницю"),
        new Announcement("Оновлення розкладу", "Перевірте розклад на понеділок"));
  }
}
