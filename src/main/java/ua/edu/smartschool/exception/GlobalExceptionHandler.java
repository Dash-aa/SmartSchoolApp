package ua.edu.smartschool.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальний обробник винятків для всього застосунку.
 * Перехоплює необроблені помилки, логує їх і показує користувачу зрозуміле повідомлення.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Перехоплює всі необроблені винятки в застосунку.
     *
     * @param ex виняток
     * @param request HTTP-запит
     * @param model модель для передачі даних на сторінку помилки
     * @return сторінка помилки
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
        String errorId = UUID.randomUUID().toString();

        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        String queryString = request.getQueryString();

        logger.error(
                "Критична помилка. errorId={}, method={}, uri={}, query={}, message={}",
                errorId,
                method,
                requestUri,
                queryString,
                ex.getMessage(),
                ex);

        model.addAttribute("errorId", errorId);
        model.addAttribute("message", "Сталася внутрішня помилка. Спробуйте пізніше.");
        model.addAttribute("details", "Якщо помилка повторюється, зверніться до адміністратора.");
        return "error/500";
    }
}