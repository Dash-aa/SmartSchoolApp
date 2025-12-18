package ua.edu.smartschool.service;

import org.springframework.stereotype.Service;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(u -> u.getPasswordHash().equals(password));
    }

    // повертає Optional з текстом помилки, або empty якщо успіх
    public Optional<String> register(RegisterForm form) {
        if (userRepository.findByLogin(form.getLogin()).isPresent()) {
            return Optional.of("Login already exists");
        }
        if (form.getRole() == null) {
            return Optional.of("Role is required");
        }
        if (form.getPassword() == null || form.getConfirmPassword() == null) {
            return Optional.of("Password is required");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            return Optional.of("Passwords do not match");
        }

        userRepository.save(
                new User(
                        form.getLogin(),
                        form.getPassword(),
                        form.getRole(),
                        form.getFullName(),
                        form.getEmail()
                )
        );

        userRepository.save(
                new User(
                        form.getLogin(),
                        form.getPassword(),
                        form.getRole(),
                        form.getFullName(),
                        form.getEmail()
                )
        );
        return Optional.empty();
    }
}
