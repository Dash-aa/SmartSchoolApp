package ua.edu.smartschool.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.edu.smartschool.model.Announcement;

/**
 * Сервіс для роботи з оголошеннями. Надає список актуальних оголошень для відображення у
 * застосунку.
 */
@Service
public class AnnouncementService {

  private static final Logger logger = LoggerFactory.getLogger(AnnouncementService.class);

  /** Список актуальних оголошень для відображення на головній сторінці. */
  private static final List<Announcement> ACTUAL_ANNOUNCEMENTS =
      List.of(
          new Announcement(
              "Початок навчального семестру", "Навчальний семестр розпочинається 1 вересня."),
          new Announcement(
              "Олімпіада з програмування", "Реєстрація на олімпіаду триває до 15 жовтня."),
          new Announcement("Батьківські збори", "Збори відбудуться у п'ятницю о 18:00."));

  /**
   * Повертає список актуальних оголошень.
   *
   * @return список оголошень
   */
  public List<Announcement> getActualAnnouncements() {
    logger.debug("Запит списку актуальних оголошень");
    return ACTUAL_ANNOUNCEMENTS;
  }
}
