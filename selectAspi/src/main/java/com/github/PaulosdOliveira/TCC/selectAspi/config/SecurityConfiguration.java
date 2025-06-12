package com.github.PaulosdOliveira.TCC.selectAspi.config;

import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/candidato").permitAll();
                    auth.requestMatchers("/candidato/login").permitAll();
                    auth.requestMatchers("/empresa/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public GrantedAuthorityDefaults simpleGrantedAuthority() {
        return new GrantedAuthorityDefaults("");
    }


}
