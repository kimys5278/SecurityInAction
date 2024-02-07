package com.springboot.ssiach8ex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{


//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//
//        var user1 = User.withUsername("kim")
//                .password("1234")
//                .roles("ADMIN")
//                .build();
//
//        var user2 = User.withUsername("park")
//                .password("12345")
//                .roles("MANAGER")
//                .build();
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//
//        return manager;
//
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

///////////////////////HelloController/////////////////////////
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.httpBasic();
//        http.csrf().disable();
//        http
//                .authorizeRequests()
//                .mvcMatchers("hello").hasRole("ADMIN")
//                .mvcMatchers("hola").hasRole("MANAGER")
//                .anyRequest().authenticated();
//
//        return http.build();
//
//    }

    ///////////////////////TestController/////////////////////////
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.httpBasic();
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.GET,"/a")
//                .authenticated()
//                .mvcMatchers(HttpMethod.POST,"/a")
//                .permitAll()
//                .anyRequest()
//                .permitAll();
//
//        http.authorizeRequests()
//                .mvcMatchers("/a/**")
//                .authenticated()
//                .anyRequest()
//                .permitAll();
//
//        return http.build();
//    }


    ///////////////////////ProductController/////////////////////////
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.httpBasic();
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .mvcMatchers("/product/{code:^[0-9]*$}")
//                .permitAll()
//                .anyRequest()
//                .denyAll();
//        return http.build();
//    }


//////////////////////AntMatchers/////////////////////////
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic();
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/hello").authenticated();
        return http.build();
    }

    //mvcMatchers 와 antMatchers의 차이점
    /*
    * mvcMatchers는 /hello하면  /hello/등 하위경로까지 다 적용됨.
    * antMatchers는 /hello만 인증하고, /hello/부터는 하위경로에는 적용x,/hello/**헤야함
    * antMatchers는 권한 부여 규칙을 적용할 모든 경로에 확실하게 적용해야함.
    */




}