package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
public class ServiceController {

    @GetMapping("/welcome")
    public String secondService() {
        return "welcome to the second service!";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. THis is message from First Service.";
    }

}
