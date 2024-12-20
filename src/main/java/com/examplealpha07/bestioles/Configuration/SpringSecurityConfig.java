package com.examplealpha07.bestioles.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/persons/all").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/persons/create").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/person/").hasRole("USER");
            //permettre à tout le monde d'accéder à l'URL racine
            //auth.requestMatchers("/*").permitAll();
            //Permettre à tout le monde d’accéder aux ressources statiques (img, css, …)
            //auth.requestMatchers("/css/*").permitAll();
            //auth.requestMatchers("/images/*").permitAll();
            //Toutes autres url et méthodes HTTP ne sont pas permises
            auth.anyRequest().denyAll();
        }).httpBasic(httpBasic -> {});
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();
        UserDetails userAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin456"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, userAdmin);
    }
}
