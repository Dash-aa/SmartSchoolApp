package ua.edu.smartschool.service;

import ua.edu.smartschool.model.Announcement;

import java.util.List;

public class AnnouncementService {
    public List<Announcement> getActualAnnouncements() {
        return List.of(
                new Announcement("Збори", "Збори учнів о 15:00"),
                new Announcement("Контрольна", "Контрольна з математики у пʼятницю"),
                new Announcement("Оновлення розкладу", "Перевірте розклад на понеділок")
        );
    }
}
