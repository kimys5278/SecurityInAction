package com.spring.ssiach5ex1.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private final String username;
    private final String password;
    private final String authority;

    public User (String username,String password, String  authority){
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(() -> authority);
    }

    public String getPassword(){
        return password;
    }

   public String getUsername(){
        return username;
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    public boolean isEnabled(){
        return true;
    }







}
