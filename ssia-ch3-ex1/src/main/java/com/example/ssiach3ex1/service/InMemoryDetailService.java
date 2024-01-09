package com.example.ssiach3ex1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryDetailService implements UserDetailsService {
    private final List<UserDetails> users;

    public InMemoryDetailService(List<UserDetails> users){
        this.users = users;
    }

    @Override
    public 	UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{

        return users.stream()
                .filter(
                        u -> u.getUsername().equals(username)
                )
                .findFirst()
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found!")
                );



    }




}
