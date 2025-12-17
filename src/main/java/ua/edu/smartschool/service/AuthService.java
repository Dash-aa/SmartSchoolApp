package ua.edu.smartschool.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.InMemoryUserRepository;

import java.util.Optional;

public class AuthService {

    private final InMemoryUserRepository users;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(InMemoryUserRepository users) {
        this.users = users;
        seedIfEmpty();
    }

    private void seedIfEmpty() {
        if (users.count() > 0) return;

        users.save(new User("admin", encoder.encode("Admin!1234"), Role.ADMIN, "Адміністратор", "admin@school.ua"));
        users.save(new User("teacher_math", encoder.encode("Teacher!1234"), Role.TEACHER, "Вчитель Математики", "math@school.ua"));
        users.save(new User("student1", encoder.encode("Student!1234"), Role.STUDENT, "Учень 1", "s1@school.ua"));
    }

    public Optional<String> register(RegisterForm form) {
        if (users.existsByLogin(form.getLogin())) {
            return Optional.of("Користувач з таким логіном вже існує");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            return Optional.of("Паролі не співпадають");
        }

        String hash = encoder.encode(form.getPassword());
        users.save(new User(form.getLogin(), hash, form.getRole(), form.getFullName(), form.getEmail()));
        return Optional.empty();
    }

    public Optional<User> login(String login, String password) {
        return users.findByLogin(login)
                .filter(u -> encoder.matches(password, u.getPasswordHash()));
    }
}
