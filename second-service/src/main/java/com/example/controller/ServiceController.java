package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class ServiceController {

    @GetMapping("/welcome")
    public String secondService() {
        return "welcome to the second service!";
    }

}
