package me.projects.AICodeTesting.Config;

import me.projects.AICodeTesting.Service.OidcService.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CustomOidcUserService customOidcUserService){
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                auth->auth.requestMatchers("/api/testing/public/**","/error").permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2Login(oauth->
                        oauth.userInfoEndpoint(userInfo->
                                userInfo.oidcUserService(customOidcUserService)
                        )
                                .defaultSuccessUrl("/api/testing/home",true)
                );
        return httpSecurity.build();
    }
}
