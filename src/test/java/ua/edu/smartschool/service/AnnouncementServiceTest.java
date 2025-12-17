package ua.edu.smartschool.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementServiceTest {

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
