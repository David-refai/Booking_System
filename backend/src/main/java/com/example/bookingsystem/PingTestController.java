package com.example.bookingsystem;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingTestController {


    @GetMapping("/ping")
    public String ping() {
        return "pot";
    }

//1. Create a new controller called PingTestController.java
//2. Add the following code to the PingTestController.java file:
//






}
