package com.Shadows.SpringZ.security;

import com.Shadows.SpringZ.security.jwt.AuthEntryPointJwt;
import com.Shadows.SpringZ.security.jwt.AuthTokenFilter;
import com.Shadows.SpringZ.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration.
 *
 * Requirements:
 * - Protect JSON API endpoints with JWT (Postman usage).
 * - Keep the existing Thymeleaf pages working without adding CSRF tokens everywhere.
 *
 * Design:
 * - Two filter chains (well-known Spring Security pattern for isolating concerns):
 *   1) /api/** => stateless JWT auth
 *   2) everything else => permitAll
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final AuthTokenFilter authTokenFilter;

    public WebSecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            AuthEntryPointJwt unauthorizedHandler,
            AuthTokenFilter authTokenFilter
    ) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authTokenFilter = authTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new LegacyCompatiblePasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/auth/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain mvcFilterChain(HttpSecurity http) throws Exception {
        // Permit the existing MVC + Thymeleaf pages.
        // CSRF is disabled to avoid breaking existing form POST endpoints.
        http.securityMatcher("/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}
