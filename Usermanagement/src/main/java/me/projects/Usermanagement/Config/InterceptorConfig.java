package me.projects.Usermanagement.Config;

import me.projects.Usermanagement.Interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private AuthInterceptor authInterceptor;
    public InterceptorConfig(AuthInterceptor authInterceptor){
        this.authInterceptor=authInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/users/**","/api/users")
                .excludePathPatterns("/api/users/save");
    }


}
