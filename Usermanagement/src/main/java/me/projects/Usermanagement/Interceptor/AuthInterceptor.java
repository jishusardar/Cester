package me.projects.Usermanagement.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.projects.Usermanagement.Service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private UserService userService;
    public AuthInterceptor(UserService userService){
        this.userService=userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        String Usersubject=request.getHeader("X-User-Auth");
        boolean userExist= userService.findUserBySubject(Usersubject);
        if(Usersubject==null||Usersubject.isBlank()||!userExist){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
