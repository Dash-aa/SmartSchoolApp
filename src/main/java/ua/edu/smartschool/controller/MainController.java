package ua.edu.smartschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Головний контролер веб-застосунку SmartSchool. Відповідає за обробку запитів на головну сторінку,
 * сторінки реєстрації, входу, кабінету користувача та контактів.
 */
@Controller
public class MainController {

  // Логер для відстеження подій контролера
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  private final AnnouncementService announcementService = new AnnouncementService();

  private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
  private final AuthService authService = new AuthService(userRepository);

  /**
   * Відображає головну сторінку застосунку. Додає до моделі назву закладу, список актуальних
   * оголошень та поточного користувача із сесії.
   *
   * @param model модель для передачі даних у шаблон
   * @param session HTTP-сесія користувача
   * @return ім'я шаблону головної сторінки
   */
  @GetMapping("/")
  public String home(Model model, HttpSession session) {
    logger.info("Відкрито головну сторінку");
    model.addAttribute("schoolName", "Інформаційна система закладу освіти");
    model.addAttribute("announcements", announcementService.getActualAnnouncements());
    model.addAttribute("currentUser", session.getAttribute("user"));
    return "home";
  }

  /**
   * Відображає сторінку реєстрації користувача. Додає до моделі порожню форму реєстрації та
   * доступні ролі.
   *
   * @param model модель для передачі даних у шаблон
   * @return ім'я шаблону сторінки реєстрації
   */
  @GetMapping("/register")
  public String registerPage(Model model) {
    logger.info("Відкрито сторінку реєстрації");
    model.addAttribute("registerForm", new RegisterForm());
    model.addAttribute("roles", Role.values());
    return "register";
  }

  /**
   * Обробляє форму реєстрації нового користувача. Перевіряє валідність введених даних і виконує
   * реєстрацію через сервіс авторизації.
   *
   * @param form форма реєстрації
   * @param bindingResult результат валідації форми
   * @param model модель для передачі повідомлень про помилки
   * @return сторінка реєстрації у випадку помилки або редирект на сторінку входу
   */
  @PostMapping("/register")
  public String doRegister(
      @Valid @ModelAttribute("registerForm") RegisterForm form,
      BindingResult bindingResult,
      Model model) {
    logger.info("Спроба реєстрації користувача {}", form.getLogin());
    model.addAttribute("roles", Role.values());

    if (bindingResult.hasErrors()) {
      logger.warn("Помилка валідації при реєстрації {}", form.getLogin());
      return "register";
    }

    var error = authService.register(form);
    if (error.isPresent()) {
      logger.warn("Помилка реєстрації {}: {}", form.getLogin(), error.get());
      model.addAttribute("formError", error.get());
      return "register";
    }
    logger.info("Користувача {} успішно зареєстровано", form.getLogin());
    return "redirect:/login";
  }

  /**
   * Відображає сторінку входу користувача. Додає до моделі порожню форму логіну.
   *
   * @param model модель для передачі даних у шаблон
   * @return ім'я шаблону сторінки входу
   */
  @GetMapping("/login")
  public String loginPage(Model model) {
    logger.info("Відкрито сторінку входу");
    model.addAttribute("loginForm", new LoginForm());
    return "login";
  }

  /**
   * Обробляє форму входу користувача в систему. У разі успішної авторизації зберігає користувача в
   * сесії.
   *
   * @param form форма входу
   * @param bindingResult результат валідації форми
   * @param model модель для передачі повідомлень про помилки
   * @param session HTTP-сесія користувача
   * @return сторінка входу у випадку помилки або редирект до кабінету
   */
  @PostMapping("/login")
  public String doLogin(
      @Valid @ModelAttribute("loginForm") LoginForm form,
      BindingResult bindingResult,
      Model model,
      HttpSession session) {
    logger.debug("Спроба входу користувача {}", form.getLogin());
    if (bindingResult.hasErrors()) {
      logger.warn("Помилка валідації при вході {}", form.getLogin());
      return "login";
    }

    var userOpt = authService.login(form.getLogin(), form.getPassword());
    if (userOpt.isEmpty()) {
      logger.warn("Невдала спроба входу {}", form.getLogin());
      model.addAttribute("authError", "Невірний логін або пароль");
      return "login";
    }
    logger.info("Користувач {} увійшов у систему", form.getLogin());
    session.setAttribute("user", userOpt.get());
    return "redirect:/cabinet";
  }

  /**
   * Відображає особистий кабінет користувача. Якщо користувач не авторизований, виконує редирект на
   * сторінку входу.
   *
   * @param model модель для передачі даних у шаблон
   * @param session HTTP-сесія користувача
   * @return ім'я шаблону кабінету або редирект на сторінку входу
   */
  @GetMapping("/cabinet")
  public String cabinet(Model model, HttpSession session) {
    Object u = session.getAttribute("user");
    if (u == null) {
      logger.warn("Спроба доступу до кабінету без авторизації");
      return "redirect:/login";
    }
    logger.info("Відкрито кабінет користувача");
    model.addAttribute("user", (User) u);
    return "cabinet";
  }

  /**
   * Виконує вихід користувача із системи. Очищає поточну HTTP-сесію та повертає на головну
   * сторінку.
   *
   * @param session HTTP-сесія користувача
   * @return редирект на головну сторінку
   */
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    logger.info("Користувач вийшов із системи");
    session.invalidate();
    return "redirect:/";
  }

  /**
   * Відображає сторінку з інформацією про застосунок і контактами.
   *
   * @param model модель для передачі даних у шаблон
   * @return ім'я шаблону сторінки "Про нас"
   */
  @GetMapping("/about")
  public String about(Model model) {
    logger.info("Відкрито сторінку 'Про нас'");
    model.addAttribute("schoolName", "Інформаційна система закладу освіти");
    model.addAttribute("contacts", "Email: office@school.ua, Тел: +38(000)000-00-00");
    return "about";
  }
}
