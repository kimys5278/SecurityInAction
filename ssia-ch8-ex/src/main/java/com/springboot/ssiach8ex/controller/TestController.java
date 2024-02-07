package com.springboot.ssiach8ex.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/a/gg")
    public String getEnpointA(){
        return "get";
    }

    @PostMapping("/a")
    public String getEnpointB(){
        return "post";
    }

    @PutMapping("/pupu")
    public String getEnpointC(){
        return "put";
    }

    @DeleteMapping("/dd")
    public String getEnpointD(){
        return "delete";
    }
}
