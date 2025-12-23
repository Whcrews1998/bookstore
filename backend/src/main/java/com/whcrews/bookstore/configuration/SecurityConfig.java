package com.whcrews.bookstore.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOption -> frameOption.disable()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register", "/login", "/images/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler((req, res, auth) -> {
                            res.setStatus(HttpServletResponse.SC_OK);
                        }).failureHandler((req, res, ex) -> {
                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
                        }). permitAll()
                ).exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, authEx) -> {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}
