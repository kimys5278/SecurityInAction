package com.ssia.ssiach2ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //클래스를 구성클래스로 표시한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean // 반환된 값을 스프링 컨텍스트에 빈으로 추가하도록 스프링에 지시
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        //주어진 사용자 이름,암호,권한 목록으로 사용자 생성.
        var user = User.withUsername("John")
                .password("12345")
                .authorities("read")
                .build();
        userDetailsService.createUser(user); //userDetailsService 에서 관리하도록 사용자 추가.

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().permitAll();

    }

}
