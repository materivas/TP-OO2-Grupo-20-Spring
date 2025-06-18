package com.oo2.grupo20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/admin/**").hasRole("ADMIN")
            		.requestMatchers("/empleado/**").hasAnyRole("EMPLEADO", "ADMIN")
            		.requestMatchers("/cliente/**").hasAnyRole("CLIENTE", "ADMIN")

                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                
            )
            .formLogin(login -> login
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout").permitAll()
            );

        return http.build();
    }
}
