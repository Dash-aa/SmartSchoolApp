package ua.edu.smartschool.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.edu.smartschool.model.User;

/**
 * Фільтр, який додає контекстну інформацію до MDC для логування.
 * У логи потрапляють користувач, сесія та URI запиту.
 */
@Component
public class LoggingContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {
            Object userObj = request.getSession().getAttribute("user");

            String username = "anonymous";
            if (userObj instanceof User user) {
                username = user.getLogin();
            }

            MDC.put("user", username);
            MDC.put("sessionId", request.getSession().getId());
            MDC.put("requestUri", request.getRequestURI());

            filterChain.doFilter(request, response);

        } finally {
            MDC.clear();
        }
    }
}