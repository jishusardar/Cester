package me.projects.AICodeTesting.Intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.projects.AICodeTesting.Service.Testing.TestingService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class TokenIntercepter implements HandlerInterceptor {
    private TestingService testingService;
    public TokenIntercepter(TestingService testingService) {
        this.testingService = testingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        OidcUser oidcUser=(OidcUser) authentication.getPrincipal();
        if(!testingService.user_have_credits(oidcUser.getSubject())){
            response.setStatus(402);
            response.setContentType("application/json");
            response.getWriter().write("{\n" +
                    "    \"message\":\"Insuffient Credits\"\n" +
                    "}");
            return false;
        }
        return true;
    }
}
