package ua.edu.smartschool.model;

import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong SEQ = new AtomicLong(1);

    private final long id;
    private final String login;
    private final String passwordHash;
    private final Role role;
    private final String fullName;
    private final String email;

    public User(String login, String passwordHash, Role role, String fullName, String email) {
        this.id = SEQ.getAndIncrement();
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
    }

    public long getId() { return id; }
    public String getLogin() { return login; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}
