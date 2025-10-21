package com.example.libraryapp.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    // Declare bean that configures security rules
    @Bean
    public SecurityFilterChain secureHttp(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Anyone can use "/users" endpoint without logging in
                        .requestMatchers("/signup","/books", "/books/search", "/users/**").permitAll()
                        // Everything else requires logging in
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }
}
