package com.rental.rental_property_management;

import com.rental.rental_property_management.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Inside your SecurityConfig.java file:

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**")) // Keeping CSRF disabled as per your current setting

                .authorizeHttpRequests(auth -> auth
                        // 1. PUBLIC VIEWS AND STATIC ASSETS (GET requests)
                        // This list ensures all your view templates are accessible for unauthenticated users
                        .requestMatchers("/", "/login", "/browse", "/user/register",
                                "/register", "/css/**", "/js/**", "/landing.html",
                                "/sell","/property/**","/images/**","/browse/**"
                        ).permitAll()

                        // 2. RESTRICTED ENDPOINTS (POST/WRITE actions)
                        // The API endpoint for submitting the property form (POST /api/properties)
                        // Requires the user to be authenticated (logged in).
                        .requestMatchers(HttpMethod.POST, "/api/properties").authenticated()

                        // 3. SECURE ALL OTHER REQUESTS
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/landing.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // no encoding
        return provider;
    }
}
