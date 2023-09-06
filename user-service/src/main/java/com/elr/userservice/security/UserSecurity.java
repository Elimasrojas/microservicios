package com.elr.userservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurity {


    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests((ahttp) -> ahttp.anyRequest().authenticated())
                .oauth2ResourceServer((oauth2ResServer) -> {
                    oauth2ResServer.jwt(jwt-> {});
                }).build();
    }

}
