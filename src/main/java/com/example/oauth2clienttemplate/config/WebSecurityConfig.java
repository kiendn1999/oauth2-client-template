package com.example.oauth2clienttemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = { // Các URL không cần xác thực
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URLS) // Cho phép truy cập vào các URL không cần xác thực
                .permitAll() // Cho phép truy cập vào các URL không cần xác thực
                .requestMatchers("/api/**") // Các URL bắt đầu bằng /api/ cần xác thực
                .authenticated()
                .and()
                .oauth2Login(oauth2login -> oauth2login.loginPage("/oauth2/authorization/messaging-client-oidc")) // Đăng nhập bằng OAuth2
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }
}