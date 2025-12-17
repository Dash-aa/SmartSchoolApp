package ua.edu.smartschool.model;

public class Announcement {
    private final String title;
    private final String text;

    public Announcement(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() { return title; }
    public String getText() { return text; }
}
