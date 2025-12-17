package ua.edu.smartschool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginForm {

    @NotBlank(message = "Логін є обов'язковим")
    @Size(min = 3, max = 32, message = "Логін має бути від 3 до 32 символів")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Логін може містити лише латиницю, цифри та _")
    private String login;

    @NotBlank(message = "Пароль є обов'язковим")
    @Size(min = 6, max = 64, message = "Пароль має бути від 6 до 64 символів")
    private String password;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
