package ua.edu.smartschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.repository.InMemoryUserRepository;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(new InMemoryUserRepository());
    }

    @Test
    void login_shouldReturnUser_whenCredentialsCorrect() {
        var user = authService.login("student1", "Student!1234");
        assertTrue(user.isPresent());
        assertEquals("student1", user.get().getLogin());
    }

    @Test
    void login_shouldReturnEmpty_whenPasswordWrong() {
        var user = authService.login("student1", "Wrong!1234");
        assertTrue(user.isEmpty());
    }

    @Test
    void login_shouldReturnEmpty_whenUserNotExists() {
        var user = authService.login("unknown", "Student!1234");
        assertTrue(user.isEmpty());
    }

    @Test
    void register_shouldFail_whenPasswordsDoNotMatch() {
        RegisterForm form = new RegisterForm();
        form.setLogin("new_user");
        form.setFullName("Test User");
        form.setEmail("test@school.ua");
        form.setRole(Role.STUDENT);
        form.setPassword("Student!1234");
        form.setConfirmPassword("Student!0000");

        var error = authService.register(form);
        assertTrue(error.isPresent());
    }

    @Test
    void register_shouldSucceed_whenDataValid() {
        RegisterForm form = new RegisterForm();
        form.setLogin("new_user");
        form.setFullName("New User");
        form.setEmail("new@school.ua");
        form.setRole(Role.TEACHER);
        form.setPassword("Teacher!1234");
        form.setConfirmPassword("Teacher!1234");

        var error = authService.register(form);
        assertTrue(error.isEmpty());

        var user = authService.login("new_user", "Teacher!1234");
        assertTrue(user.isPresent());
        assertEquals(Role.TEACHER, user.get().getRole());
    }
}
