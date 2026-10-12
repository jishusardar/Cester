package me.projects.AICodeTesting.Config;

import me.projects.AICodeTesting.Intercepter.TokenIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    private TokenIntercepter tokenIntercepter;
    public IntercepterConfig(TokenIntercepter tokenIntercepter){
        this.tokenIntercepter=tokenIntercepter;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercepter).addPathPatterns("/api/testing/code/**");
    }
}
