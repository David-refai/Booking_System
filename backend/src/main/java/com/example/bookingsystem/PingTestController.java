package com.example.bookingsystem;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingTestController {


    @GetMapping("/ping")
    public String ping() {
        return "pot";
    }


    @GetMapping("/ping2")
    public String ping2() {
        return "pot2";
    }






}
