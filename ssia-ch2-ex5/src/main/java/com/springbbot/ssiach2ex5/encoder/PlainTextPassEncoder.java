package com.springbbot.ssiach2ex5.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

//가장 단순한 PasswordEncoder 구현
public class PlainTextPassEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword){
        return rawPassword.toString(); //암호를 변경하지 않고, 그대로 반환
}
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        return rawPassword.equals(encodedPassword);
    }

}
