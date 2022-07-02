package hyun6ik.shoppingmall.global.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String AJAX_REQUEST = "x-requested-with";
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String LOGIN_URL = "/login";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (XML_HTTP_REQUEST.equals(request.getHeader(AJAX_REQUEST))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED);
            return;
        }
        response.sendRedirect(LOGIN_URL);
    }
}
