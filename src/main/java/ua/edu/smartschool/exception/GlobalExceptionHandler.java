package ua.edu.smartschool.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Глобальний обробник винятків для всього застосунку. */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private final MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(Exception.class)
  public String handleException(
      Exception ex, HttpServletRequest request, Model model, Locale locale) {
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
    model.addAttribute("message", messageSource.getMessage("error.internal", null, locale));
    model.addAttribute("details", messageSource.getMessage("error.contact", null, locale));
    model.addAttribute("reportText", messageSource.getMessage("error.report", null, locale));
    model.addAttribute("backHomeText", messageSource.getMessage("error.backhome", null, locale));

    return "error/500";
  }
}
