package ua.edu.smartschool.model;

/**
 * Клас, що представляє оголошення у системі.
 * Містить заголовок та текст оголошення.
 */
public class Announcement {
  private final String title;
  private final String text;

  /**
   * Створює нове оголошення.
   *
   * @param title заголовок оголошення
   * @param text текст оголошення
   */
  public Announcement(String title, String text) {
    this.title = title;
    this.text = text;
  }

  /**
   * Повертає заголовок оголошення.
   *
   * @return заголовок оголошення
   */
  public String getTitle() {
    return title;
  }

  /**
   * Повертає текст оголошення.
   *
   * @return текст оголошення
   */
  public String getText() {
    return text;
  }
}