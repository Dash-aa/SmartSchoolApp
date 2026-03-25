package ua.edu.smartschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.smartschool.dto.LoginForm;
import ua.edu.smartschool.dto.RegisterForm;
import ua.edu.smartschool.model.Role;
import ua.edu.smartschool.model.User;
import ua.edu.smartschool.repository.InMemoryUserRepository;
import ua.edu.smartschool.service.AnnouncementService;
import ua.edu.smartschool.service.AuthService;

/**
 * Головний контролер веб-застосунку SmartSchool.
 * Відповідає за обробку запитів на головну сторінку,
 * сторінки реєстрації, входу, кабінету користувача та контактів.
 */

@Controller
public class MainController {

    private final AnnouncementService announcementService = new AnnouncementService();

    private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private final AuthService authService = new AuthService(userRepository);

    /**
     * Відображає головну сторінку застосунку.
     * Додає до моделі назву закладу, список актуальних оголошень
     * та поточного користувача із сесії.
     *
     * @param model модель для передачі даних у шаблон
     * @param session HTTP-сесія користувача
     * @return ім'я шаблону головної сторінки
     */
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("schoolName", "Інформаційна система закладу освіти");
        model.addAttribute("announcements", announcementService.getActualAnnouncements());
        model.addAttribute("currentUser", session.getAttribute("user"));
        return "home";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("roles", Role.values());
        return "register";
    }
    /**
     * Обробляє форму реєстрації нового користувача.
     * Перевіряє валідність введених даних і виконує реєстрацію через сервіс авторизації.
     *
     * @param form форма реєстрації
     * @param bindingResult результат валідації форми
     * @param model модель для передачі повідомлень про помилки
     * @return сторінка реєстрації у випадку помилки або редирект на сторінку входу
     */
    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("registerForm") RegisterForm form,
                             BindingResult bindingResult,
                             Model model) {

        model.addAttribute("roles", Role.values());

        if (bindingResult.hasErrors()) {
            return "register";
        }

        var error = authService.register(form);
        if (error.isPresent()) {
            model.addAttribute("formError", error.get());
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    /**
     * Обробляє форму входу користувача в систему.
     * У разі успішної авторизації зберігає користувача в сесії.
     *
     * @param form форма входу
     * @param bindingResult результат валідації форми
     * @param model модель для передачі повідомлень про помилки
     * @param session HTTP-сесія користувача
     * @return сторінка входу у випадку помилки або редирект до кабінету
     */
    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("loginForm") LoginForm form,
                          BindingResult bindingResult,
                          Model model,
                          HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        var userOpt = authService.login(form.getLogin(), form.getPassword());
        if (userOpt.isEmpty()) {
            model.addAttribute("authError", "Невірний логін або пароль");
            return "login";
        }

        session.setAttribute("user", userOpt.get());
        return "redirect:/cabinet";
    }

    @GetMapping("/cabinet")
    public String cabinet(Model model, HttpSession session) {
        Object u = session.getAttribute("user");
        if (u == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", (User) u);
        return "cabinet";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("schoolName", "Інформаційна система закладу освіти");
        model.addAttribute("contacts", "Email: office@school.ua, Тел: +38(000)000-00-00");
        return "about";
    }
}
