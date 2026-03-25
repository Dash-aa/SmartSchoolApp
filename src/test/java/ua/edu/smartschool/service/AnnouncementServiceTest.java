package ua.edu.smartschool.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Тести для сервісу оголошень. Перевіряють коректність отримання списку актуальних оголошень. */
class AnnouncementServiceTest {

  /**
   * Перевіряє, що метод повертає непорожній список оголошень з валідними заголовками та текстом.
   */
  @Test
  void getActualAnnouncements_shouldReturnNonEmptyList() {
    AnnouncementService service = new AnnouncementService();
    var list = service.getActualAnnouncements();

    assertNotNull(list);
    assertFalse(list.isEmpty());
    assertTrue(list.stream().allMatch(a -> !a.getTitle().isBlank()));
    assertTrue(list.stream().allMatch(a -> !a.getText().isBlank()));
  }
}
