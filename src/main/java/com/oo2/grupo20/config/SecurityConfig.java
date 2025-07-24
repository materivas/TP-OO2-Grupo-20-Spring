package com.oo2.grupo20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableMethodSecurity(prePostEnabled = true)
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
            		
            	// Swagger
            	.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            	// Login REST
            	
            	.requestMatchers("/api/auth/login").permitAll()	
                // Recursos públicos
                .requestMatchers("/css/**", "/js/**", "/img/**", "/login").permitAll()
                .requestMatchers("/cliente/new", "/cliente/create").permitAll()

                .requestMatchers("/turno/index", "/turno/detail/**").hasAnyRole("EMPLEADO", "ADMIN", "CLIENTE")
                .requestMatchers("/turno/**").hasAnyRole("ADMIN", "CLIENTE")


                // Otros accesos por rol
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/empleado/**").hasRole("ADMIN")
                .requestMatchers("/cliente/**").hasAnyRole("ADMIN", "CLIENTE")
                .requestMatchers("/servicio/**", "/especialidad/**", "/establecimiento/**", "/dia/**").hasRole("ADMIN")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            // resto del config
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/loginprocess")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout_success")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(eh -> eh
                .accessDeniedPage("/error/403")
            );

        return http.build();
    }


}
