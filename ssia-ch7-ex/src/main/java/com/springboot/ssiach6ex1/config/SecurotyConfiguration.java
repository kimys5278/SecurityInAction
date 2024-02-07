package com.springboot.ssiach6ex1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurotyConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        var manager = new InMemoryUserDetailsManager(); // 두 사용자를 저장하는 InMemoryUserDetailsManager를 선언.

        var user1 = User.withUsername("John") // 첫 번째 사용자는 READ 권한을 가짐.
                .password("1234")
                //.authorities("ROLE_MANAGER")
                .roles("MANAGER")
                .build();

        var user2 = User.withUsername("Tony") // 두 번째 사용자는 WRITE 권한을 가짐.
                .password("a1234")
                //.authorities("ROLE_ADMIN")
                .roles("ADMIN")
                .build();
        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest().permitAll(); //모든 요청에 엑세스를 허용한다.
//    }

    // 만약 WRITE 권한이 있는 사용자만 허용하려면
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest()
//                .hasAuthority("WRITE"); // 사용자가 엔드포인트에 접근하기 위한 조건 지정.
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest()
//                .hasAnyAuthority("READ","WRITE"); // 사용자가 엔드포인트에 접근하기 위한 조건 여러개 지정가능 지정.
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest()
//                .access("hasAuthority('WRITE')");
//        // access 사용 hasAuthority와 hasAnyAuthority 사용못할때 사용.
//        // access를 사용하면 매우 복잡해짐.
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic();

        http.authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN");
        // access 사용 hasAuthority와 hasAnyAuthority 사용못할때 사용.
        // access를 사용하면 매우 복잡해짐.
    }
}
