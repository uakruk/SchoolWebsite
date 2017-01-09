package ua.edu.kordelschool.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        clearAuthenticationAttributes(request);
        String redirectUrl = null;
        if (session != null) {
            redirectUrl = (String) session.getAttribute("url_redirect_login");
        }
        if (redirectUrl != null) {
            session.removeAttribute("url_prior_login");
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect("/dashboard");
        }

    }
}