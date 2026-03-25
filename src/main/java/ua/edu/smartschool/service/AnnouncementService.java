package ua.edu.smartschool.service;

import java.util.List;
import ua.edu.smartschool.model.Announcement;

/**
 * Сервіс для роботи з оголошеннями.
 * Надає список актуальних оголошень для відображення у застосунку.
 */
public class AnnouncementService {

  /**
   * Повертає список актуальних оголошень.
   *
   * @return список оголошень
   */
  public List<Announcement> getActualAnnouncements() {
    return List.of(
            new Announcement("Збори", "Збори учнів о 15:00"),
            new Announcement("Контрольна", "Контрольна з математики у пʼятницю"),
            new Announcement("Оновлення розкладу", "Перевірте розклад на понеділок"));
  }
}