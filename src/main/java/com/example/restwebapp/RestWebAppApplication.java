package com.example.restwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
public class RestWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWebAppApplication.class, args);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                .formLogin(withDefaults());
        return http.build();
    }

}
