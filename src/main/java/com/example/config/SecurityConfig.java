package com.example.config;

import com.example.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final AuthEntryPointJwt authEntryPointJwt;

    private final JwtFilter jwtFilter;

    private final PasswordEncoder passwordEncoder;

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/",
            "/v3/api-docs//**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/"
    };

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, AuthEntryPointJwt authEntryPointJwt, JwtFilter jwtFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPointJwt = authEntryPointJwt;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors();

        http
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http
                .authorizeHttpRequests()
                .and().httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/attach/public/**").permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/api/services/public/**").permitAll()
                .requestMatchers("/api/services_data/public/**").permitAll()
                .requestMatchers("/api/services_button/public/**").permitAll()
                .requestMatchers("/api/doctorsirdarya/public/**").permitAll()
                .requestMatchers("/api/news/public/**").permitAll()
                .requestMatchers("/api/doctortashkent/public/**").permitAll()
                .requestMatchers("/api/resume/public/save_resume").permitAll()
                .requestMatchers("/api/patient/public/create_patient").permitAll()
                .anyRequest()
                .authenticated()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPointJwt);
        return http.build();
    }


}


