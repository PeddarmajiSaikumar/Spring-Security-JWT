package com.example.spring_security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity  //doesn't allow default flow,used to go with the given flow.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                 .csrf(Customizer->Customizer.disable())
                 .authorizeHttpRequests(request->request.anyRequest().authenticated())
                 // .formLogin(Customizer.withDefaults())//form login for browser
                 .httpBasic(Customizer.withDefaults()) //used to get the data from postman
                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(Customizer->Customizer.disable());
//        http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//        // http.formLogin(Customizer.withDefaults()); //form login for browser
//        http.httpBasic(Customizer.withDefaults()); //used to get the data from postman
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
}
