package ua.edu.smartschool.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.repository.InMemoryUserRepository;
import ua.edu.smartschool.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private InMemoryUserRepository userRepository;

    @BeforeEach
    void resetRepo() {
        userRepository.clear();
        // можна заново додати seed за потреби (або залишити порожньо)
    }

    @Test
    void IT_1_shouldRegisterAndThenLoginSuccessfully() {
        RegisterForm form = new RegisterForm();
        form.setLogin("integration_user");
        form.setRole(Role.STUDENT);
        form.setPassword("Student!1234");
        form.setConfirmPassword("Student!1234");

        var error = authService.register(form);
        assertTrue(error.isEmpty(), "Registration should succeed");

        var loggedIn = authService.login("integration_user", "Student!1234");
        assertTrue(loggedIn.isPresent(), "User should be able to login after registration");
        assertEquals(Role.STUDENT, loggedIn.get().getRole());
    }

    @Test
    void IT_2_shouldFailLoginWithWrongPasswordAfterRegister() {
        RegisterForm form = new RegisterForm();
        form.setLogin("integration_user2");
        form.setRole(Role.TEACHER);
        form.setPassword("Teacher!1234");
        form.setConfirmPassword("Teacher!1234");

        assertTrue(authService.register(form).isEmpty());

        var loggedIn = authService.login("integration_user2", "Wrong!1234");
        assertTrue(loggedIn.isEmpty(), "Login must fail with wrong password");
    }
}

