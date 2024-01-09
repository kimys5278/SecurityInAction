package com.springbbot.ssiach2ex5.entity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class BuliderUser {
    //빌더형식은 계약의 구현을 아용할 필요 X
    UserDetails u = User.withUsername("bill")
            .password("12345")
            .authorities("read","write")
            .accountExpired(false)
            .disabled(true)
            .credentialsExpired(true)
            .build();
}
