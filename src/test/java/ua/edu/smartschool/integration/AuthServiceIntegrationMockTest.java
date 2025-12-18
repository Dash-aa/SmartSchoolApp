package ua.edu.smartschool.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.edu.smartschool.repository.UserRepository;
import ua.edu.smartschool.service.AuthService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthServiceIntegrationMockTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void IT_3_shouldFailLoginWhenRepositoryReturnsEmpty_mockedDependency() {
        when(userRepository.findByLogin("ghost")).thenReturn(Optional.empty());

        var result = authService.login("ghost", "Any!1234");
        assertTrue(result.isEmpty());
    }
}
