package com.example.spring_security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity  //doesn't allow default flow,used to go with the given flow.
public class SecurityConfig {

    @Autowired
    private UserDetailsService  userDetailsService;
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                 .csrf(Customizer->Customizer.disable())
                 .authorizeHttpRequests(request->request.anyRequest().authenticated())
                 .formLogin(Customizer.withDefaults())//form login for browser
                 .httpBasic(Customizer.withDefaults()) //used to get the data from postman
                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("saikumar")
//                .password("sai@123")
//                .roles("USER")
//                .build();
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("sowmya")
//                .password("sowmya@123")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();  // Use NoOpPasswordEncoder
//    }
      @Bean
      public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password hashing
      }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return new ProviderManager(authenticationProvider());
    }

    public AuthenticationProvider  authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
