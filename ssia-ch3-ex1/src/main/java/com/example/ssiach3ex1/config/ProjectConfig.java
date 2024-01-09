package com.example.ssiach3ex1.config;


import com.example.ssiach3ex1.entity.User;
import com.example.ssiach3ex1.service.InMemoryDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u = new User("Kim","1234","read");
        List<UserDetails> users = List.of(u);
        return new InMemoryDetailService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //DelegatingPasswordEncoder 등록
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        Map<String,PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("noop",NoOpPasswordEncoder.getInstance());
//        encoders.put("bcrypt",new BCryptPasswordEncoder());
//        encoders.put("scrypt",new SCryptPasswordEncoder());
//
//        return new DelegatingPasswordEncoder("bcrypt",encoders);
//    }
}
