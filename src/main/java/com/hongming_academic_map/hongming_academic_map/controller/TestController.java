package com.hongming_academic_map.hongming_academic_map.controller;

 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
 
 
@RestController
public class TestController {
 
    @GetMapping("/test")
    public String test(){
        return "Hello Wrold !";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
}