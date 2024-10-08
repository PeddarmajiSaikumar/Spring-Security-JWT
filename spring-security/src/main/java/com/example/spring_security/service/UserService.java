package com.example.spring_security.service;
import com.example.spring_security.model.Users;
import com.example.spring_security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

//    public String login(Users user) {
//        String pwdDb=myUserDetailsService.loadUserByUsername(user.getUsername()).getPassword();
//        String decodedPwd=passwordEncoder.encode(user.getPassword());
//        log.info("Password at login{}", user.getPassword());
//        log.info("Password at database{}", decodedPwd);
//
//        Users user1 = userRepository.findByUsername(user.getUsername());
//        if (user1 == null) {
//            return "User not found";
//        }
//        if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
//            return "Login successful";
//        }
//        return "Login failed";
//    }

    public String Verify(Users user){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        throw new UsernameNotFoundException("Invalid user request");
    }
}