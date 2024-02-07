package com.springboot.ssiach8ex.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("hola")
    public String hola(){
        return "hola";
    }




}
