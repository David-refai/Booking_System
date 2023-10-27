package com.example.bookingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingPongController {
    record PingPongResponse(String response) {}

    @GetMapping("/ping")
    public PingPongResponse ping() {

        return new PingPongResponse("pong from booking system test 2");
    }

//1. Create a new controller called PingTestController.java

}
